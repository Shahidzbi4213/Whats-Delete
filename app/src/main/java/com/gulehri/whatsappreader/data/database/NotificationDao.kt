package com.gulehri.whatsappreader.data.database


// Created by Shahid Iqbal on 12/26/2022.

import androidx.room.*
import com.gulehri.whatsappreader.data.models.SingleNotification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(singleNotification: SingleNotification)

    @Delete
    suspend fun delete(singleNotification: SingleNotification)

    @Query("DELETE  FROM notifications")
    suspend fun deleteAll()

    @Query("SELECT * FROM notifications order by postTime")
    fun getAll(): Flow<List<SingleNotification>>

}