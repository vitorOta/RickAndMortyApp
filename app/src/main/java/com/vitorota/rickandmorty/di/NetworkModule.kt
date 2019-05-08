package com.vitorota.rickandmorty.di

import com.vitorota.rickandmorty.RETROFIT_CACHE_SIZE
import com.vitorota.rickandmorty.data.network.ApiClientBuilder
import com.vitorota.rickandmorty.data.network.BASE_URL
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */


val NetworkModule = module {
    single {
        ApiClientBuilder.createServiceApi(
            serviceClass = RickAndMortyApi::class.java,
            baseUrl = BASE_URL,
            cacheConfig = ApiClientBuilder.CacheConfig(androidContext(), RETROFIT_CACHE_SIZE)
        )
    }
}