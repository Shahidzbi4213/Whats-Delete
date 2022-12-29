package com.gulehri.whatsappreader.data.viewmodels

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulehri.whatsappreader.data.repositories.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


// Created by Shahid Iqbal on 12/26/2022.

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repo: NotificationRepository) :
    ViewModel() {

    val readNotification = repo.readNotifications.stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )


    fun deleteAll() = viewModelScope.launch {
        repo.deleteAll()
    }
}