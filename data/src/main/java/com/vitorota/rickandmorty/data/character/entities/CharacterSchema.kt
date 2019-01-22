package com.vitorota.rickandmorty.data.character.entities

import com.google.gson.annotations.SerializedName
import com.vitorota.rickandmorty.data.Schema
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.episode.entities.EpisodeSchema
import com.vitorota.rickandmorty.data.location.entities.LocationSchema
import com.vitorota.rickandmorty.data.util.toDomain

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
    var origin: EpisodeSchema?,
    var location: LocationSchema?,
    var image: String,
    var episode: List<EpisodeSchema>?
) :Schema<Character> {
    override fun toDomain(): Character =
            Character(
                id,
                name,
                status,
                species,
                type,
                gender,
                origin?.toDomain(),
                location?.toDomain(),
                image,
                episode?.toDomain()
            )
}