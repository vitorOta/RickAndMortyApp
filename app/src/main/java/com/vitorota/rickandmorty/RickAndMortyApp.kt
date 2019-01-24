package com.vitorota.rickandmorty

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber
import timber.log.Timber.plant
import android.graphics.Bitmap
import com.facebook.imagepipeline.core.ImagePipelineConfig



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
        }else{
            //TODO Crash reporting three logging for production app
        }
    }

}