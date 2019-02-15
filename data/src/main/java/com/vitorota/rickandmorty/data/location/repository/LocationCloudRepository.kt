package com.vitorota.rickandmorty.data.location.repository

import com.vitorota.rickandmorty.data.BaseCloudRepository
import com.vitorota.rickandmorty.data.ListResponse
import com.vitorota.rickandmorty.data.location.entity.Location
import com.vitorota.rickandmorty.data.location.schemas.LocationSchema
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import kotlinx.coroutines.Deferred

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class LocationCloudRepository(api: RickAndMortyApi) : BaseCloudRepository<Location, LocationSchema>(api),
    LocationRepository {
    override val getMethod: (Int) -> Deferred<LocationSchema>
        get() = api::getLocation
    override val listMethod: (Int) -> Deferred<ListResponse<LocationSchema>>
        get() = api::listLocations
}