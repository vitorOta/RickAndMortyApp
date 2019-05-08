package com.vitorota.rickandmorty.features.character.list

import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.character.usecase.ListCharactersUseCase
import com.vitorota.rickandmorty.features.BaseViewModel
import com.vitorota.rickandmorty.utils.asyncAwait

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */
class ListCharacterViewModel
    (private val useCase: ListCharactersUseCase) : BaseViewModel<List<Character>>() {
    suspend fun loadCharacters() {
        doWorkWithProgress {
            asyncAwait {
                useCase.execute(ListCharactersUseCase.Params())
            }
        }
    }
}