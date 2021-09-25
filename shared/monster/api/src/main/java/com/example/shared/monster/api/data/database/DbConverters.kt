package com.example.shared.monster.api.data.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class DbConverters {

    @TypeConverter
    fun fromTimestamp(value: Long): LocalDateTime =
        LocalDateTime.ofInstant(
            Instant.ofEpochMilli(value),
            ZoneId.systemDefault(),
        )

    @TypeConverter
    fun localDateTimeToTimestamp(date: LocalDateTime): Long =
        date
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
}
