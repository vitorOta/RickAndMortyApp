package com.vitorota.rickandmorty.data.character.repository

import com.vitorota.rickandmorty.data.BaseCloudRepository
import com.vitorota.rickandmorty.data.ListResponse
import com.vitorota.rickandmorty.data.character.entities.CharacterSchema
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import kotlinx.coroutines.Deferred
import javax.inject.Inject

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class CharacterCloudRepository @Inject constructor(api: RickAndMortyApi) :
    BaseCloudRepository<CharacterSchema, Character>(api) {
    override val getMethod: (Int) -> Deferred<CharacterSchema>
        get() = api::getCharacter
    override val listMethod: (Int) -> Deferred<ListResponse<CharacterSchema>>
        get() = api::listCharacters
}