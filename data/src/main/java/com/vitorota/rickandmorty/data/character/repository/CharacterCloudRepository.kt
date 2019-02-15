package com.vitorota.rickandmorty.data.character.repository

import com.vitorota.rickandmorty.data.BaseCloudRepository
import com.vitorota.rickandmorty.data.ListResponse
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.character.schemas.CharacterSchema
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import kotlinx.coroutines.Deferred

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class CharacterCloudRepository constructor(api: RickAndMortyApi) :
    BaseCloudRepository<Character, CharacterSchema>(api), CharacterRepository {
    override val getMethod: (Int) -> Deferred<CharacterSchema>
        get() = api::getCharacter
    override val listMethod: (Int) -> Deferred<ListResponse<CharacterSchema>>
        get() = api::listCharacters
}