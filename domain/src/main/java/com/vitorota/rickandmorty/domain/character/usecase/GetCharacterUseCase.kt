package com.vitorota.rickandmorty.data.character.usecase

import com.vitorota.rickandmorty.data.UseCase
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.character.repository.CharacterRepository

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
class GetCharacterUseCase(private val repo:CharacterRepository) :UseCase<Character?, GetCharacterUseCase.Params> {
    suspend override fun execute(params: Params): Character? = repo.get(params.id)

    data class Params(val id:Int)
}