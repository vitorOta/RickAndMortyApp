package com.vitorota.rickandmorty.core.di

import com.vitorota.rickandmorty.core.di.data.NetworkModule
import com.vitorota.rickandmorty.core.di.features.CharacterModule
import com.vitorota.rickandmorty.features.character.list.ListCharacterViewModel
import dagger.Component
import javax.inject.Singleton

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */

@Singleton
@Component(
    modules = arrayOf(
        NetworkModule::class,
        CharacterModule::class
    )
)
interface ViewModelComponent {

    fun inject(viewModel: ListCharacterViewModel)
}