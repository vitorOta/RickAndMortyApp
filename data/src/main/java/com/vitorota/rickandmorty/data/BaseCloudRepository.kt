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
abstract class BaseCloudRepository<T, S>(protected val api: RickAndMortyApi) : Repository<T> where S : Schema<T> {

    var syncRequestGet = SynchronousRequestManagerImpl<S>()
    var syncRequestList = SynchronousRequestManagerImpl<ListResponse<S>>()

    abstract val getMethod: (Int) -> Deferred<S>
    abstract val listMethod: (Int) -> Deferred<ListResponse<S>>

    @Throws(DataIOException::class, DataHttpException::class)
    override suspend fun get(id: Int): T? {
        val result = syncRequestGet.getResult(getMethod(id))
        return result.toDomain()
    }

    @Throws(DataIOException::class, DataHttpException::class)
    suspend fun listResponse(page: Int): ListResponse<S> {
        val result = syncRequestList.getResult(listMethod(page))
        return result
    }

    @Throws(DataIOException::class, DataHttpException::class)
    override suspend fun list(page: Int): List<T> {
        return listResponse(page).results.toDomain()
    }

    override suspend fun insert(t: T) = throw UnsupportedOperationException()
    override suspend fun update(t: T) = throw UnsupportedOperationException()
    override suspend fun delete(t: T) = throw UnsupportedOperationException()

}