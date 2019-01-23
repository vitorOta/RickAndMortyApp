package com.vitorota.rickandmorty.data.character.usecase

import com.vitorota.rickandmorty.data.UseCase
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.character.repository.CharacterRepository

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
class ListCharactersUseCase(private val repo: CharacterRepository) :
    UseCase<List<Character>?, ListCharactersUseCase.Params> {

    suspend override fun execute(params: Params): List<Character> = repo.list(params.page)

    data class Params(val page: Int = 1)
}