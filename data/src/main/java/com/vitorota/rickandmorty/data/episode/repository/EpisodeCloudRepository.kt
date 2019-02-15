package com.vitorota.rickandmorty.data.episode.repository

import com.vitorota.rickandmorty.data.BaseCloudRepository
import com.vitorota.rickandmorty.data.ListResponse
import com.vitorota.rickandmorty.data.episode.entity.Episode
import com.vitorota.rickandmorty.data.episode.schemas.EpisodeSchema
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import kotlinx.coroutines.Deferred

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class EpisodeCloudRepository(api: RickAndMortyApi) : BaseCloudRepository<Episode, EpisodeSchema>(api),
    EpisodeRepository {
    override val getMethod: (Int) -> Deferred<EpisodeSchema>
        get() = api::getEpisode
    override val listMethod: (Int) -> Deferred<ListResponse<EpisodeSchema>>
        get() = api::listEpisodes
}