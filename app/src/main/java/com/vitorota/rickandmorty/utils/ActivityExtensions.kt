package com.vitorota.rickandmorty.utils

import android.app.Activity
import android.app.AlertDialog
import com.vitorota.rickandmorty.R


/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */

fun Activity.showAlert(message: Int) {
    AlertDialog.Builder(this).setMessage(message)
        .show()
}


inline fun Activity.createLoadingDialog(): AlertDialog{
    val loadingView = this.layoutInflater.inflate(R.layout.dialog_loading, null)

    val dialog = AlertDialog.Builder(this)
        .setView(loadingView)
        .setCancelable(false)
        .create()

    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window!!.setLayout(10, 10)

    return dialog
}