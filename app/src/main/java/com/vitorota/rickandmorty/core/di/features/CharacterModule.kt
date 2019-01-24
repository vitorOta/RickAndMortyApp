package com.vitorota.rickandmorty.core.di.features

import com.vitorota.rickandmorty.data.character.repository.CharacterCloudRepository
import com.vitorota.rickandmorty.data.character.repository.CharacterRepository
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */

@Module
class CharacterModule {

    @Provides
    @Inject
    fun getRepository( api: RickAndMortyApi): CharacterRepository=
            CharacterCloudRepository(api)
}