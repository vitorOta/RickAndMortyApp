package com.vitorota.rickandmorty.data

import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import com.vitorota.rickandmorty.data.network.requests.SynchronousRequestManagerImpl
import com.vitorota.rickandmorty.data.util.exceptions.DataHttpException
import com.vitorota.rickandmorty.data.util.exceptions.DataIOException
import com.vitorota.rickandmorty.data.util.toDomain
import kotlinx.coroutines.Deferred

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
abstract class BaseCloudRepository<T, S>(protected val api: RickAndMortyApi) : Repository<S> where T : Schema<S> {

    var syncRequestGet = SynchronousRequestManagerImpl<T>()
    var syncRequestList = SynchronousRequestManagerImpl<ListResponse<T>>()

    abstract val getMethod: (Int) -> Deferred<T>
    abstract val listMethod: (Int) -> Deferred<ListResponse<T>>

    @Throws(DataIOException::class, DataHttpException::class)
    override suspend fun get(id: Int): S? {
        val result = syncRequestGet.getResult(getMethod(id))
        return result.toDomain()
    }

    @Throws(DataIOException::class, DataHttpException::class)
    suspend fun listResponse(page: Int): ListResponse<T> {
        val result = syncRequestList.getResult(listMethod(page))
        return result
    }

    @Throws(DataIOException::class, DataHttpException::class)
    override suspend fun list(page: Int): List<S> {
        return listResponse(page).results.toDomain()
    }

}