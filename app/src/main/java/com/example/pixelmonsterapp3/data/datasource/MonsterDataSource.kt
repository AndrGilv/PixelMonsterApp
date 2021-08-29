package com.example.pixelmonsterapp3.data.datasource

import com.example.pixelmonsterapp3.domain.entity.*
import kotlinx.coroutines.flow.Flow

interface MonsterDataSource {

    fun getRandomMonster() : Flow<Result<Monster>>

    fun getGeneratedMonsterListFlow(): Flow<Result<List<Monster>>>

    fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>>

    fun deleteMonster(id: Int): Flow<Result<Unit>>

    fun deleteAllMonsters(): Flow<Result<Unit>>
}