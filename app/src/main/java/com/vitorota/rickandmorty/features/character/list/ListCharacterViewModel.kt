package com.vitorota.rickandmorty.features.character.list

import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.character.usecase.ListCharactersUseCase
import com.vitorota.rickandmorty.features.BaseViewModel
import com.vitorota.rickandmorty.utils.asyncAwait
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
        //inject dependencies
        injector.inject(this)
    }

    suspend fun loadCharacters() {
        doWorkWithProgress {
            asyncAwait {
                useCase.execute(ListCharactersUseCase.Params())
            }
        }
    }
}