package com.vitorota.rickandmorty.widgets.detailedcharacter

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.widgets.loadAsyncImage

class DetailedCharacterConfigurationActivity : ComponentActivity() {
    //since this is just to study, I'll hard-code the options here
    private val options = listOf(
        1 to "Ricky",
        2 to "Morty",
        3 to "Summer",
        4 to "Beth",
        5 to "Jerry",
    )

    private val widgetId by lazy {
        intent.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCompatTheme {
                PickCharacterList()
            }
        }
    }

    @Composable
    fun PickCharacterList() {
        LazyColumn {
            items(options) { opt ->
                Row(modifier = Modifier.padding(8.dp)) {
                    ClickableText(
                        text = AnnotatedString(opt.second),
                        style = TextStyle(fontSize = 16.sp),
                        onClick = {
                            selectCharacter(opt)
                        })
                }
            }
        }
    }

    fun selectCharacter(opt: Pair<Int, String>) {
        val appWidgetManager = AppWidgetManager.getInstance(this)

        //setup the view
        val views = RemoteViews(packageName, R.layout.widget_detailed_character)

        //could do a background request here, but for now, let's just emulate it
        val (id, name) = opt
        views.setTextViewText(R.id.widget_tvName, name)
        val imageUrl = "https://rickandmortyapi.com/api/character/avatar/$id.jpeg"
        views.loadAsyncImage(this, R.id.widget_imageView, widgetId, imageUrl)

        appWidgetManager.updateAppWidget(widgetId, views)

        //set the result
        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId)
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }
}