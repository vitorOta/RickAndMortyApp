package com.vitorota.rickandmorty.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.features.character.details.CharacterDetailsFragmentArgs
import kotlin.random.Random.Default.nextInt

class RandomCharacterAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { viewId ->

            val remoteView = RemoteViews(context.packageName, R.layout.widget_random_character)

//TODO change to dynamic instead of hardcoded
            val characterId = nextInt(826) + 1
//image
            val widgetTarget = AppWidgetTarget(context, R.id.widget_imageView, remoteView, viewId)
            Glide.with(context.applicationContext)
                .asBitmap()
                .load("https://rickandmortyapi.com/api/character/avatar/$characterId.jpeg")
                .into(widgetTarget)

            //click
            val pendingIntent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.characterDetailsFragment)
                .setArguments(CharacterDetailsFragmentArgs(characterId, null, null).toBundle())
                .createPendingIntent()
            remoteView.setOnClickPendingIntent(R.id.widget_imageView, pendingIntent)

            appWidgetManager.updateAppWidget(viewId, remoteView)
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }
}