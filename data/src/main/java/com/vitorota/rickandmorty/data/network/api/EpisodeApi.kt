package com.vitorota.rickandmorty.data.network.api

import com.vitorota.rickandmorty.data.character.entities.CharacterSchema
import com.vitorota.rickandmorty.data.episode.entities.EpisodeSchema
import com.vitorota.rickandmorty.data.episode.entity.Episode
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */


interface EpisodeApi : Api<EpisodeSchema, Episode> {

    @GET("episode/{id}")
    override fun get(id: Int): Deferred<EpisodeSchema>

    @GET("episode")
    override fun list(page: Int): Deferred<List<EpisodeSchema>>

}