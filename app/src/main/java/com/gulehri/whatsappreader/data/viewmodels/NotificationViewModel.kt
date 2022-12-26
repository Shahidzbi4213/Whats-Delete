package com.gulehri.whatsappreader.data.viewmodels

import android.service.notification.StatusBarNotification
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.gulehri.whatsappreader.data.models.SingleNotification
import com.gulehri.whatsappreader.data.repositories.NotificationRepository
import com.gulehri.whatsappreader.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


// Created by Shahid Iqbal on 12/26/2022.

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repo: NotificationRepository) :
    ViewModel() {

    val readNotification = repo.readNotifications.stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )
}