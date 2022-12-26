package com.gulehri.whatsappreader.data.repositories

import android.os.Build
import android.service.notification.StatusBarNotification
import androidx.annotation.RequiresApi
import com.gulehri.whatsappreader.data.database.AppDatabase
import com.gulehri.whatsappreader.data.models.SingleNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


// Created by Shahid Iqbal on 12/26/2022.

class NotificationRepository @Inject constructor(private val db: AppDatabase) {

    val readNotifications = db.mainDao().getAll()


    fun saveNotifications(sbn: StatusBarNotification?) {
        val notification = SingleNotification(
            title = sbn?.notification?.extras?.getString("android.title"),
            text = sbn?.notification?.extras?.getString("android.text"),
            postTime = SimpleDateFormat(
                "dd/MM/yyyy  hh:mm:ss a",
                Locale.ENGLISH
            ).format(sbn?.postTime)
        )

        CoroutineScope(Dispatchers.IO).launch {
            db.mainDao().insert(notification)
        }
    }

}