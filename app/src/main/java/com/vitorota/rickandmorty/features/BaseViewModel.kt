package com.vitorota.rickandmorty.features

import androidx.lifecycle.*
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.util.exceptions.DataHttpException
import com.vitorota.rickandmorty.data.util.exceptions.DataIOException
import com.vitorota.rickandmorty.utils.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */
open class BaseViewModel<T> : ViewModel() {
    private val showProgressEvent = SingleLiveEvent<Void>()
    private val hideProgressEvent = SingleLiveEvent<Void>()
    private val showErrorEvent = SingleLiveEvent<Int>()

    private val _data: MutableLiveData<T> = MutableLiveData()

    open val data: LiveData<T> = _data

    var triedLoadAtLeastOnce: Boolean = false
        private set

    //for compose
    private val _isRefreshingFlow = MutableStateFlow(false)
    val isRefreshingFlow = _isRefreshingFlow as StateFlow<Boolean>

    protected suspend fun doWorkWithProgress(loadData: suspend () -> T) {
        showProgressEvent.call()
        _isRefreshingFlow.update { true }
        triedLoadAtLeastOnce = true
        try {
            _data.value = loadData()
        } catch (e: Exception) {
            Timber.e(e.message)
            showErrorEvent.value = when (e) {
                is DataIOException -> R.string.error_io
                is DataHttpException ->
                    when (e.statusCode) {
                        404 -> R.string.error_404
                        else -> R.string.error_http_general
                    }
                else -> throw e
            }
        } finally {
            hideProgressEvent.call()
            _isRefreshingFlow.update { false }
        }
    }

    fun observe(
        owner: LifecycleOwner,
        showProgress: () -> Unit,
        hideProgress: () -> Unit,
        showError: (Int) -> Unit,
        handleData: (T) -> Unit

    ) {
        showProgressEvent.observe(owner, showProgress)
        hideProgressEvent.observe(owner, hideProgress)
        showErrorEvent.observe(owner, Observer(showError))
        data.observe(owner, Observer(handleData))
    }
}