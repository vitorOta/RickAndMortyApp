package com.vitorota.rickandmorty.features

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.vitorota.rickandmorty.R
import kotlinx.coroutines.delay
import timber.log.Timber

/**
 *
 * @author Vitor Ota
 * @since 06/05/2019
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //illustrating the problem with unique works + Widgets
        //https://commonsware.com/blog/2018/11/24/workmanager-app-widgets-side-effects.html
        //https://issuetracker.google.com/issues/115575872
        val request = OneTimeWorkRequestBuilder<UploadWorker>().build()
        WorkManager.getInstance(applicationContext).enqueue(request)

    }
}

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Timber.d("running worker *********")
        return Result.success()
    }
}