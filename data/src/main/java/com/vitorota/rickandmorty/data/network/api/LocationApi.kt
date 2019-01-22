package com.vitorota.rickandmorty.data.network.api

import com.vitorota.rickandmorty.data.location.entities.LocationSchema
import com.vitorota.rickandmorty.data.location.entity.Location
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */


interface LocationApi : Api<LocationSchema, Location> {

    @GET("location/{id}")
    override fun get(id: Int): Deferred<LocationSchema>

    @GET("location")
    override fun list(page: Int): Deferred<List<LocationSchema>>

}