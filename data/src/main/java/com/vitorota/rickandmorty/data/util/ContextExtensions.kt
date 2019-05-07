package com.vitorota.rickandmorty.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 *
 * @author Vitor Ota
 * @since 07/05/2019
 */

val Context.hasNetwork: Boolean
    get() = run {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        activeNetwork != null && activeNetwork.isConnected
    }