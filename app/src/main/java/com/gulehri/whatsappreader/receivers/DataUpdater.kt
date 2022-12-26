package com.gulehri.whatsappreader.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.service.notification.StatusBarNotification
import android.util.Log
import com.google.gson.Gson
import com.gulehri.whatsappreader.data.repositories.NotificationRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// Created by Shahid Iqbal on 12/26/2022.

@AndroidEntryPoint
class DataUpdater @Inject constructor() :
    BroadcastReceiver() {

    @Inject
    lateinit var repository: NotificationRepository

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 != null) {
            val sbn = Gson().fromJson(p1.getStringExtra("sbn"), StatusBarNotification::class.java)
            //repository.saveNotification(sbn)
        }

    }

}