package com.vitorota.rickandmorty.widgets.detailedcharacter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import timber.log.Timber

class DetailedCharacterWidgetProvider : AppWidgetProvider() {
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