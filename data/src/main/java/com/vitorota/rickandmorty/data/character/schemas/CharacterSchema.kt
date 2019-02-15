package com.vitorota.rickandmorty.data.character.schemas

import com.vitorota.rickandmorty.data.Schema
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.episode.entity.Episode
import com.vitorota.rickandmorty.data.episode.schemas.EpisodeSchema
import com.vitorota.rickandmorty.data.location.entity.Location
import com.vitorota.rickandmorty.data.location.schemas.LocationSchema

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
data class CharacterSchema(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: EpisodeShortResponse, //EpisodeSchema?,
    var location: LocationShortResponse,//LocationSchema?,
    var image: String,
    var episode: List<String>?
) : Schema<Character> {
    override fun toDomain(): Character =
        Character(
            id,
            name,
            status,
            species,
            type,
            gender,
            origin.toDomain(),
            location.toDomain(),
            image,
            episode
        )
}

data class EpisodeShortResponse(var name: String, var url: String) {
    fun toDomain(): Episode = EpisodeSchema(
        try {
            url.split("/").last().toInt()
        } catch (e: Exception) {
            -1
        },
        name,
        null,
        null,
        null
    ).toDomain()

}

data class LocationShortResponse(var name: String, var url: String) {
    fun toDomain(): Location = LocationSchema(
        try {
            url.split("/").last().toInt()
        } catch (e: Exception) {
            -1
        },
        name,
        null,
        null,
        null
    ).toDomain()
}