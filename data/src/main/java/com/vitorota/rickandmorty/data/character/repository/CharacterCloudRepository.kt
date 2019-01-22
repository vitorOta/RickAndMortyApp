package com.vitorota.rickandmorty.data.character.repository

import com.vitorota.rickandmorty.data.BaseCloudRepository
import com.vitorota.rickandmorty.data.character.entities.CharacterSchema
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.network.api.CharacterApi
import javax.inject.Inject

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class CharacterCloudRepository @Inject constructor(api: CharacterApi) :
    BaseCloudRepository<CharacterSchema, Character>(api) {
}