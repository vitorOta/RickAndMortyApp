package com.vitorota.rickandmorty.utils

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.vitorota.rickandmorty.R


/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */

fun Fragment.showAlert(message: Int, okListener: DialogInterface.OnClickListener? = null) {
    AlertDialog.Builder(context).setMessage(message)
        .setPositiveButton(android.R.string.ok, okListener)
        .show()
}


inline fun Fragment.createLoadingDialog(): AlertDialog {
    val loadingView = this.layoutInflater.inflate(R.layout.dialog_loading, null)

    val dialog = AlertDialog.Builder(context)
        .setView(loadingView)
        .setCancelable(false)
        .create()

    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window!!.setLayout(10, 10)

    return dialog
}