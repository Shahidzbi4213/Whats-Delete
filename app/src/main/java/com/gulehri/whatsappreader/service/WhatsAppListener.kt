package com.gulehri.whatsappreader.service

import android.annotation.SuppressLint
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


    @SuppressLint("NewApi")
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn?.packageName in arrayOf(
                Constants.WHATSAPP, Constants.WHATSAPP_BUSINESS, Constants.INSTAGRAM
            )
        ) {
            repository.saveNotifications(sbn)


          /*  ("====================================").debug()

            sbn?.notification?.extras?.keySet()?.forEach {
                ("$it ==> ${  sbn.notification?.extras?.get(it).debug()}" )
            }

            ("=========================================").debug()*/
        }

        super.onNotificationRemoved(sbn)

    }


}