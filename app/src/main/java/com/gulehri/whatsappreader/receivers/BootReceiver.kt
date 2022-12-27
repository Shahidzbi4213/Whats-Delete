package com.gulehri.whatsappreader.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gulehri.whatsappreader.service.WhatsAppListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// Created by Shahid Iqbal on 12/26/2022.

@AndroidEntryPoint
class BootReceiver @Inject constructor() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Intent(context, WhatsAppListener::class.java).apply {
                context.startService(this)
            }

        }
    }
}
