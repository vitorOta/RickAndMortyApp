package com.vitorota.rickandmorty.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 *
 * @author Vitor Ota
 * @since 08/02/2019
 */

private val mainDispatcher = Dispatchers.Main
private val ioDispatcher = Dispatchers.IO

fun launchUI(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
        block()
    }
}

suspend fun <T> asyncAwait(block: suspend () -> T): T {
    return GlobalScope.async(Dispatchers.IO) {
        block()
    }.await()
}