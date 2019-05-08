package com.vitorota.rickandmorty.features

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.vitorota.rickandmorty.utils.createLoadingDialog
import com.vitorota.rickandmorty.utils.showAlert

/**
 *
 * @author Vitor Ota
 * @since 08/02/2019
 */
abstract class BaseFragment : Fragment() {

    protected open val loadingDialog by lazy { createLoadingDialog() }

    open fun showProgress() {
        loadingDialog.show()
    }

    open fun hideProgress() {
        loadingDialog.hide()
    }

    open fun showError(message: Int) {
        showAlert(message)
    }
}