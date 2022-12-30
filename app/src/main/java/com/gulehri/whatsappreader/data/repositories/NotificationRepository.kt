package com.gulehri.whatsappreader.data.repositories

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.service.notification.StatusBarNotification
import androidx.core.graphics.drawable.toBitmap
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


    @SuppressLint("NewApi")
    fun saveNotifications(sbn: StatusBarNotification?) {
        sbn?.notification?.extras?.let {

            val user = it.getString(Notification.EXTRA_TITLE).toString()
            val message = it.getString(Notification.EXTRA_TEXT)
            val appIcon = sbn.notification.smallIcon?.loadDrawable(context)?.toBitmap()
            val profileImage = sbn.notification.getLargeIcon()?.loadDrawable(context)?.toBitmap()


            val time = SimpleDateFormat(
                "dd/MM/yyyy  hh:mm:ss a", Locale.ENGLISH
            ).format(sbn.postTime)


            if (isMessageValid(message) && user != "WA Business" && user != "Whatsapp"
                && !it.getBoolean(Notification.EXTRA_IS_GROUP_CONVERSATION)
            ) {

                SingleNotification(
                    title = user, text = message,
                    appIcon = appIcon, postTime = time,
                    profileImage = profileImage
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

    private fun isMessageValid(message: String?): Boolean {
        return message?.let {
            message !in arrayOf(
                null,
                "Checking for new messages",
                "new message",
                "new messages",
                "Photo", "Photos", "Video", "Videos", "chats"
            ) && !message.contains("Checking for new messages", true)
                    && !message.contains("new message", true)
                    && !message.contains(
                "new messages",
                true
            )
                    && !message.contains("Photo", true)
                    && !message.contains("Photos", true)
                    && !message.contains("Video", true)
                    && !message.contains("Videos", true)
                    && !message.contains("chats", true)
                    && !message.contains("messages", true)
        } ?: true


    }
}