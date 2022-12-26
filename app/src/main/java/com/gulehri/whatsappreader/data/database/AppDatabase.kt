package com.gulehri.whatsappreader.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gulehri.whatsappreader.data.models.SingleNotification


// Created by Shahid Iqbal on 12/26/2022.

@Database(
    entities = [SingleNotification::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

   abstract fun mainDao():NotificationDao
}