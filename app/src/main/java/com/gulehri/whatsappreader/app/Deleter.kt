package com.gulehri.whatsappreader.app

import android.app.Application
import androidx.preference.PreferenceManager
import com.gulehri.whatsappreader.utils.Extensions.setMode
import dagger.hilt.android.HiltAndroidApp


// Created by Shahid Iqbal on 12/26/2022.

@HiltAndroidApp
class Deleter : Application() {


    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isNightMode = sharedPreferences.getBoolean("dark", false)
        isNightMode.setMode()
    }


}