package com.gulehri.whatsappreader.data.database

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream


// Created by Shahid Iqbal on 12/29/2022.


class IconConverter() {

    @SuppressLint("NewApi")
    @TypeConverter
    fun fromIcon(icon: Bitmap?): ByteArray? {
        return if (icon == null) {
            null
        } else {
            val outputStream = ByteArrayOutputStream()
            icon.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.toByteArray()
        }
    }

    @TypeConverter
    fun toIcon(iconByteArray: ByteArray?): Bitmap? {
        return if (iconByteArray == null) {
            null
        } else {
            BitmapFactory.decodeByteArray(iconByteArray, 0, iconByteArray.size)
        }
    }
}
