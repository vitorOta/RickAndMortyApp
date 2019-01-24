package com.vitorota.rickandmorty.core.di.data

import com.vitorota.rickandmorty.data.network.ApiClientBuilder
import com.vitorota.rickandmorty.data.network.BASE_URL
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi =
        ApiClientBuilder.createServiceApi(
            RickAndMortyApi::class.java,
            BASE_URL
        )

}