package com.vitorota.rickandmorty.features.character.list

import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.character.usecase.ListCharactersUseCase
import com.vitorota.rickandmorty.features.BaseViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import javax.inject.Inject

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */
class ListCharacterViewModel : BaseViewModel<List<Character>>() {
    @Inject
    lateinit var useCase: ListCharactersUseCase



    init {
        MainScope().run {
            async { loadCharacters() }
        }
    }


    private suspend fun loadCharacters() {
        doWorkWithProgress {
            useCase.execute(ListCharactersUseCase.Params())
        }

    }

}