package com.vitorota.rickandmorty

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
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
        setupFresco()
        setupTimber()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(
                NetworkModule,
                CharacterModule
            )
        }
    }

    private fun setupFresco() {
//        val config = ImagePipelineConfig.newBuilder(this)
//            .setBitmapsConfig(Bitmap.Config.RGB_565)
//            .setDownsampleEnabled(true)
//            .build()
//        Fresco.initialize(this, config)
        Fresco.initialize(this)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        } else {
            //TODO Crash reporting three logging for production app
        }
    }

}