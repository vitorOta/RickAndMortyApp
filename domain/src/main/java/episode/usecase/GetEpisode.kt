package com.vitorota.rickandmorty.data.episode.usecase

import com.vitorota.rickandmorty.data.UseCase
import com.vitorota.rickandmorty.data.character.usecase.GetCharacterUseCase
import com.vitorota.rickandmorty.data.episode.entity.Episode
import com.vitorota.rickandmorty.data.episode.repository.EpisodeRepository

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
class GetEpisode(private val repo: EpisodeRepository) : UseCase<Episode, GetCharacterUseCase.Params> {
    override fun execute(params: GetCharacterUseCase.Params): Episode = repo.get(params.id)

    data class Params(val id: Int)
}
