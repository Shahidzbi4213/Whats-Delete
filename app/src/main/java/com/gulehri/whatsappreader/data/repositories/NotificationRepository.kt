package com.gulehri.whatsappreader.data.repositories

import android.app.Notification
import android.content.Context
import android.os.Build
import android.service.notification.StatusBarNotification
import com.gulehri.whatsappreader.data.database.AppDatabase
import com.gulehri.whatsappreader.data.models.SingleNotification
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


// Created by Shahid Iqbal on 12/26/2022.

class NotificationRepository @Inject constructor(
    private val db: AppDatabase, @ApplicationContext private val context: Context
) {

    val readNotifications = db.mainDao().getAll()


    fun saveNotifications(sbn: StatusBarNotification?) {
        sbn?.notification?.extras?.let {

            val user = it.getString(Notification.EXTRA_TITLE).toString()
            val message = it.getString(Notification.EXTRA_TEXT) ?: ""
            val detailMessage = it.getCharSequenceArray(Notification.EXTRA_TEXT_LINES)?.joinToString("\n")


            val time = SimpleDateFormat(
                "dd/MM/yyyy  hh:mm:ss a", Locale.ENGLISH
            ).format(sbn.postTime)

            if (!message.contains("new message", true)
                && !message.contains("photo", true)
                && !message.contains("video", true)
                && !message.contains("photos", true)
                && !message.contains("videos", true)
            ) {

                SingleNotification(
                    title = user,
                    text = message,
                    detail = detailMessage,
                    postTime = time
                )
            } else null


        }.also {
            it?.let { notifications ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.mainDao().insert(notifications)
                }
            }
        }
    }

    suspend fun deleteAll() {
        try {
            db.mainDao().deleteAll()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}