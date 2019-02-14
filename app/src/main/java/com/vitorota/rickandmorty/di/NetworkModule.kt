package com.vitorota.rickandmorty.di

import com.vitorota.rickandmorty.data.network.ApiClientBuilder
import com.vitorota.rickandmorty.data.network.BASE_URL
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import org.koin.dsl.module.module

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */

val NetworkModule = module {
    factory {
        ApiClientBuilder.createServiceApi(
            RickAndMortyApi::class.java,
            BASE_URL
        )
    }
}