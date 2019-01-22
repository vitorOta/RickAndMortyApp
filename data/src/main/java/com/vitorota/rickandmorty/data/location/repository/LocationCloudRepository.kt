package com.vitorota.rickandmorty.data.location.repository

import com.vitorota.rickandmorty.data.BaseCloudRepository
import com.vitorota.rickandmorty.data.location.entities.LocationSchema
import com.vitorota.rickandmorty.data.location.entity.Location
import com.vitorota.rickandmorty.data.network.api.Api

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class LocationCloudRepository(api: Api<LocationSchema, Location>) : BaseCloudRepository<LocationSchema, Location>(api) {
}