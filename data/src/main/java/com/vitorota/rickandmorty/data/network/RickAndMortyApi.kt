package com.vitorota.rickandmorty.data.network

import com.vitorota.rickandmorty.data.ListResponse
import com.vitorota.rickandmorty.data.character.schemas.CharacterSchema
import com.vitorota.rickandmorty.data.episode.schemas.EpisodeSchema
import com.vitorota.rickandmorty.data.location.schemas.LocationSchema
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @author Vitor Ota
 * @since 23/01/2019
 */
interface RickAndMortyApi {

//region Character

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Deferred<CharacterSchema>

    @GET("character")
    fun listCharacters(@Query("page") page: Int): Deferred<ListResponse<CharacterSchema>>

//endregion

//region Episode

    @GET("episode/{id}")
    fun getEpisode(@Path("id") id: Int): Deferred<EpisodeSchema>

    @GET("episode")
    fun listEpisodes(@Query("page") page: Int): Deferred<ListResponse<EpisodeSchema>>

//endregion

//region Location

    @GET("location/{id}")
    fun getLocation(@Path("id") id: Int): Deferred<LocationSchema>

    @GET("location")
    fun listLocations(@Query("page") page: Int): Deferred<ListResponse<LocationSchema>>

//endregion
}