package com.vitorota.rickandmorty.data.episode.schemas

import com.google.gson.annotations.SerializedName
import com.vitorota.rickandmorty.data.Schema
import com.vitorota.rickandmorty.data.episode.entity.Episode

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
data class EpisodeSchema(
    var id: Int,
    var name: String,
    @SerializedName("air_date") var airDate: String?,
    var episode: String?,
    var characters: List<String>?
) : Schema<Episode> {
    override fun toDomain(): Episode =
        Episode(
            id,
            name,
            airDate,
            episode,
            characters
        )

}