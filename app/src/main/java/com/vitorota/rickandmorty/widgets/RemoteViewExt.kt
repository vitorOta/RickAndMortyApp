package com.vitorota.rickandmorty.widgets

import android.content.Context
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget

fun RemoteViews.loadAsyncImage(
    context: Context,
    @IdRes imageViewId: Int,
    widgetId: Int,
    imageUrl: String
) {
    val widgetTarget = AppWidgetTarget(context, imageViewId, this, widgetId)
    Glide.with(context.applicationContext)
        .asBitmap()
        .load(imageUrl)
        .into(widgetTarget)
}