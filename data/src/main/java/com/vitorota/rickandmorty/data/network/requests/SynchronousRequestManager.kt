package com.vitorota.rickandmorty.data.network.requests

import kotlinx.coroutines.Deferred
import java.io.IOException

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
interface SynchronousRequestManager<T> {
    suspend fun getResult(deferred: Deferred<T>):T
}