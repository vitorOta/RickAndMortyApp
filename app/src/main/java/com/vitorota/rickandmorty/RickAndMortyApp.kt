package com.vitorota.rickandmorty

import android.app.Application
import com.vitorota.rickandmorty.di.CharacterModule
import com.vitorota.rickandmorty.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.plant


/**
 *
 * @author Vitor Ota
 * @since 23/01/2019
 */
class RickAndMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(
                listOf(
                    NetworkModule,
                    CharacterModule
                )
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        } else {
            //TODO Crash reporting three logging for production app
        }
    }

}