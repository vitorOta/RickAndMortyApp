package com.vitorota.rickandmorty.data

import com.vitorota.rickandmorty.data.network.api.Api
import com.vitorota.rickandmorty.data.network.requests.SynchronousRequestManagerImpl
import com.vitorota.rickandmorty.data.util.toDomain

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
abstract class BaseCloudRepository<T, S>(private val api: Api<T, S>) : Repository<S> where T : Schema<S> {

    var syncRequestGet = SynchronousRequestManagerImpl<T>()
    var syncRequestList = SynchronousRequestManagerImpl<List<T>>()

    override suspend fun get(id: Int): S? {
        val result = syncRequestGet.getResult(api.get(id))
        return result.toDomain()
    }

    override suspend fun list(page: Int): List<S> {
        val result = syncRequestList.getResult(api.list(page))
        return result.toDomain()
    }
}