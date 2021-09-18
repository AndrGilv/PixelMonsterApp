package com.example.pixelmonsterapp3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import javax.inject.Singleton

@Singleton
@Database(
    entities = [MonsterEntity::class, MonsterDetailsEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(value = [DbConverters::class])
abstract class MonsterDatabase : RoomDatabase() {

    abstract fun monsterDao(): MonsterDao
}
