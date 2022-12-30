package com.gulehri.whatsappreader.data.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey


// Created by Shahid Iqbal on 12/26/2022.

@Entity(tableName = "notifications")
data class SingleNotification(

    val title: String?,
    val text: String?,
    val appIcon: Bitmap?,
    val profileImage: Bitmap?,
    @PrimaryKey
    val postTime: String,
)