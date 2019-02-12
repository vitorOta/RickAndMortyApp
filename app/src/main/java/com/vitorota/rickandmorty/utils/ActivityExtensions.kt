package com.vitorota.rickandmorty.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import com.vitorota.rickandmorty.R


/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */

fun Activity.showAlert(message: Int, okListener: DialogInterface.OnClickListener? = null) {
    AlertDialog.Builder(this).setMessage(message)
        .setPositiveButton(android.R.string.ok, okListener)
        .show()
}


inline fun Activity.createLoadingDialog(): AlertDialog {
    val loadingView = this.layoutInflater.inflate(R.layout.dialog_loading, null)

    val dialog = AlertDialog.Builder(this)
        .setView(loadingView)
        .setCancelable(false)
        .create()

    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window!!.setLayout(10, 10)

    return dialog
}