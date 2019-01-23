package com.vitorota.rickandmorty.data.episode.usecase

import com.vitorota.rickandmorty.data.UseCase
import com.vitorota.rickandmorty.data.episode.entity.Episode
import com.vitorota.rickandmorty.data.episode.repository.EpisodeRepository

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
class GetEpisodeUseCase(private val repo: EpisodeRepository) : UseCase<Episode?, GetEpisodeUseCase.Params> {
    suspend override fun execute(params: GetEpisodeUseCase.Params): Episode? = repo.get(params.id)

    data class Params(val id: Int)
}
