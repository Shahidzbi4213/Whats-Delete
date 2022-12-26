package com.gulehri.whatsappreader.utils

import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import com.gulehri.whatsappreader.service.WhatsAppListener


// Created by Shahid Iqbal on 12/26/2022.

object Extensions {


    fun Context.isNotificationServiceEnable(enabled: (Boolean) -> Unit) {
        val myNotificationListenerComponentName =
            ComponentName(this, WhatsAppListener::class.java)
        val enabledListeners =
            Settings.Secure.getString(this.contentResolver, "enabled_notification_listeners")

        if (enabledListeners.isEmpty()) enabled(false)

        enabled(enabledListeners.split(":").map {
            ComponentName.unflattenFromString(it)
        }.any { componentName ->
            myNotificationListenerComponentName == componentName
        })
    }
}