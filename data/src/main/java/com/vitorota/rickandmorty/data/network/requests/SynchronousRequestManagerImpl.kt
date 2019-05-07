package com.vitorota.rickandmorty.data.network.requests

import com.vitorota.rickandmorty.data.util.exceptions.DataException
import com.vitorota.rickandmorty.data.util.exceptions.DataHttpException
import com.vitorota.rickandmorty.data.util.exceptions.DataIOException
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class SynchronousRequestManagerImpl<T> : SynchronousRequestManager<T> {
    @Throws(DataException::class)
    override suspend fun getResult(deferred: Deferred<T>): T {
        try {
            val result = deferred.await()
            return result
        } catch (e: Exception) {
            Timber.e(e)
            when (e) {
                is IOException -> throw DataIOException(e.message, e)
                is HttpException -> throw DataHttpException(e.message, e.code(), e)
                else -> throw e
            }

        }
    }
}