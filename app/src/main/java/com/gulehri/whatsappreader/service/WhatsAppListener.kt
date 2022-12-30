package com.gulehri.whatsappreader.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.gulehri.whatsappreader.data.repositories.NotificationRepository
import com.gulehri.whatsappreader.utils.Constants
import com.gulehri.whatsappreader.utils.Extensions.debug
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// Created by Shahid Iqbal on 12/26/2022.


@AndroidEntryPoint
class WhatsAppListener @Inject constructor() : NotificationListenerService() {


    @Inject
    lateinit var repository: NotificationRepository


    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn?.packageName in arrayOf(
                Constants.WHATSAPP, Constants.WHATSAPP_BUSINESS, Constants.INSTAGRAM
            )
        ) {
            "MM Called".debug()
            repository.saveNotifications(sbn)
        }

        super.onNotificationRemoved(sbn)

    }


}