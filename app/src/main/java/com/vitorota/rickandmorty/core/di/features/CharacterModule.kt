package com.vitorota.rickandmorty.core.di.features

import com.vitorota.rickandmorty.data.character.repository.CharacterCloudRepository
import com.vitorota.rickandmorty.data.character.repository.CharacterRepository
import com.vitorota.rickandmorty.data.character.usecase.GetCharacterUseCase
import com.vitorota.rickandmorty.data.character.usecase.ListCharactersUseCase
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import dagger.Module
import dagger.Provides

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */

@Module
class CharacterModule {

    @Provides
    fun provideRepository(api: RickAndMortyApi): CharacterRepository = CharacterCloudRepository(api)

    @Provides
    fun provideListCharactersUseCase(repo: CharacterRepository): ListCharactersUseCase = ListCharactersUseCase(repo)

    @Provides
    fun provideGetCharacterUseCase(repo: CharacterRepository): GetCharacterUseCase = GetCharacterUseCase(repo)
}