package com.example.shared.monster.api.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MonsterDao {

    @Query("SELECT * FROM monster ORDER BY creation_date")
    fun getMonsters(): Flow<List<MonsterEntity>>

    @Query("SELECT * FROM monster WHERE id = :id")
    suspend fun getMonsterWithDetails(id: Int): MonsterWithDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonster(vararg monsterEntity: MonsterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMonsters(monsterEntity: List<MonsterEntity>)

    @Delete
    suspend fun deleteMonster(monsterEntity: MonsterEntity)

    @Query("DELETE FROM monster WHERE id = :id")
    suspend fun deleteMonster(id: Int)

    @Query("DELETE FROM monster")
    suspend fun deleteAllMonsters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonsterDetails(monsterDetailsEntity: MonsterDetailsEntity)
}
