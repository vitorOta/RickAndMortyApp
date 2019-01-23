package com.vitorota.rickandmorty

import android.app.Application
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
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }else{
            //TODO Crash reporting three logging for production app
        }
    }

}