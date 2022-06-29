package com.vitorota.rickandmorty.widgets.randomcharacter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.features.character.details.CharacterDetailsFragmentArgs
import com.vitorota.rickandmorty.widgets.loadAsyncImage
import timber.log.Timber
import kotlin.random.Random.Default.nextInt

class RandomCharacterAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { widgetId ->

            val remoteView = RemoteViews(context.packageName, R.layout.widget_random_character)

//TODO change to dynamic instead of hardcoded
            val characterId = nextInt(826) + 1
//image
            val imageUrl = "https://rickandmortyapi.com/api/character/avatar/$characterId.jpeg"
            remoteView.loadAsyncImage(context,R.id.widget_imageView, widgetId,imageUrl)

            //click
            val pendingIntent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.characterDetailsFragment)
                .setArguments(CharacterDetailsFragmentArgs(characterId, null, null).toBundle())
                .createPendingIntent()
            remoteView.setOnClickPendingIntent(R.id.widget_imageView, pendingIntent)

            appWidgetManager.updateAppWidget(widgetId, remoteView)
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        Timber.d("Appwidget deleted")
    }

    override fun onEnabled(context: Context?) {
        Timber.d("Appwidget enabled")
    }

    override fun onDisabled(context: Context?) {
        Timber.d("Appwidget disabled")
    }
}