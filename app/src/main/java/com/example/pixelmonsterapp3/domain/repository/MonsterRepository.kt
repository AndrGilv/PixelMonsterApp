package com.example.pixelmonsterapp3.domain.repository

import com.example.pixelmonsterapp3.domain.entity.*
import kotlinx.coroutines.flow.Flow

interface MonsterRepository {

    fun generateRandomMonster(): Flow<FinishableResult>

    fun getGeneratedMonsterListFlow(): Flow<Result<List<Monster>>>

    fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>>

    fun deleteMonster(id: Int): Flow<FinishableResult>

    fun deleteAllMonsters(): Flow<FinishableResult>
}