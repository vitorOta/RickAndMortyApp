package com.vitorota.rickandmorty.data.episode.repository

import com.vitorota.rickandmorty.data.BaseCloudRepository
import com.vitorota.rickandmorty.data.episode.entities.EpisodeSchema
import com.vitorota.rickandmorty.data.episode.entity.Episode
import com.vitorota.rickandmorty.data.network.api.Api

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class EpisodeCloudRepository(api: Api<EpisodeSchema, Episode>) : BaseCloudRepository<EpisodeSchema, Episode>(api) {
}