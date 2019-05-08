package com.vitorota.rickandmorty.di

import com.vitorota.rickandmorty.data.character.repository.CharacterCloudRepository
import com.vitorota.rickandmorty.data.character.repository.CharacterRepository
import com.vitorota.rickandmorty.data.character.usecase.GetCharacterUseCase
import com.vitorota.rickandmorty.data.character.usecase.ListCharactersUseCase
import com.vitorota.rickandmorty.features.character.details.CharacterDetailsViewModel
import com.vitorota.rickandmorty.features.character.list.ListCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */


val CharacterModule = module {
    factory { CharacterCloudRepository(get()) as CharacterRepository }
    factory { ListCharactersUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
    viewModel { ListCharacterViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get()) }
}