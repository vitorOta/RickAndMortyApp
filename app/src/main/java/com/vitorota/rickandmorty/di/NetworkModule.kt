package com.vitorota.rickandmorty.di

import com.vitorota.rickandmorty.cacheSize
import com.vitorota.rickandmorty.data.network.ApiClientBuilder
import com.vitorota.rickandmorty.data.network.BASE_URL
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */


val NetworkModule = module {
    single {
        ApiClientBuilder.createServiceApi(
            context = androidContext(),
            serviceClass = RickAndMortyApi::class.java,
            baseUrl = BASE_URL,
            cacheSize = cacheSize
        )
    }
}