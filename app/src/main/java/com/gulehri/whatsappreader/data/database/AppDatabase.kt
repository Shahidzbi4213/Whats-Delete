package com.gulehri.whatsappreader.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.gulehri.whatsappreader.data.models.SingleNotification


// Created by Shahid Iqbal on 12/26/2022.

@Database(
    entities = [SingleNotification::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IconConverter::class)
abstract class AppDatabase : RoomDatabase() {

   abstract fun mainDao():NotificationDao
}