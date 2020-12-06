package com.example.myapplication

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : MultiDexApplication() {

    companion object {
        private const val LOG_TAG = "App"
        lateinit var instance: App
            private set

        val applicationContext: Context
            get() = instance.applicationContext

    }
}