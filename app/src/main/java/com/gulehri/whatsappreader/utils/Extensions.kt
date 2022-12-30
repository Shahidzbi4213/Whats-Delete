package com.gulehri.whatsappreader.utils

import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
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


    fun Boolean.setMode() {
        if (this) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    fun String.showMessage(view: View) =
        Snackbar.make(view, this, Snackbar.LENGTH_SHORT).show()


    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun FragmentActivity.setBarTitle(title: String) {
        (this as AppCompatActivity).supportActionBar?.title = title
    }

    fun Any?.debug() = Log.d("Debugger", "$this")

}