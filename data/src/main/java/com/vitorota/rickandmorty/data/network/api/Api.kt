package com.vitorota.rickandmorty.data.network.api

import com.vitorota.rickandmorty.data.Schema
import kotlinx.coroutines.Deferred
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */


interface Api<T, S> where T : Schema<S> {

    /**
     * you should add @GET annotation
     * */
    abstract fun get(@Path("id") id: Int): Deferred<T>

    /**
     * you should add @GET annotation
     * */
    abstract fun list(@Query("page") page: Int): Deferred<List<T>>

}