package com.vitorota.rickandmorty.features

import androidx.appcompat.app.AppCompatActivity
import com.vitorota.rickandmorty.utils.createLoadingDialog
import com.vitorota.rickandmorty.utils.showAlert

/**
 *
 * @author Vitor Ota
 * @since 08/02/2019
 */
open class BaseActivity : AppCompatActivity() {

    private val loadingDialog by lazy { createLoadingDialog() }

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