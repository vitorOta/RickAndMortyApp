package com.vitorota.rickandmorty.data.network.api

import com.vitorota.rickandmorty.data.character.entities.CharacterSchema
import com.vitorota.rickandmorty.data.character.entity.Character
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */


interface CharacterApi:Api<CharacterSchema,Character> {

    @GET("character/{id}")
    override fun get(id: Int): Deferred<CharacterSchema>

    @GET("character")
    override fun list(page: Int): Deferred<List<CharacterSchema>>

}