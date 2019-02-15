package com.vitorota.rickandmorty.data.location.schemas

import com.vitorota.rickandmorty.data.Schema
import com.vitorota.rickandmorty.data.location.entity.Location

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
data class LocationSchema(
    var id: Int,
    var name: String,
    var type: String?,
    var dimension: String?,
    var residents: List<String>?
):Schema<Location> {
    override fun toDomain(): Location =
            Location(
                id,
                name,
                type,
                dimension,
                residents
            )
}