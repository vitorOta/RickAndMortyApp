package com.vitorota.rickandmorty.features.character.details

import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.character.usecase.GetCharacterUseCase
import com.vitorota.rickandmorty.features.BaseViewModel
import javax.inject.Inject

/**
 *
 * @author Vitor Ota
 * @since 12/02/2019
 */
class CharacterDetailsViewModel : BaseViewModel<Character?>() {

    @Inject
    lateinit var useCase: GetCharacterUseCase

    init {
        injector.inject(this)
    }

    suspend fun loadData(characterId: Int) {
        doWorkWithProgress {
            useCase.execute(GetCharacterUseCase.Params(characterId))
        }
    }
}