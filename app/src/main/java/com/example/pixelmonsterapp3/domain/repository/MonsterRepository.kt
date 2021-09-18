package com.example.pixelmonsterapp3.domain.repository

import com.example.pixelmonsterapp3.domain.entity.FinishableResult
import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.domain.entity.Result
import kotlinx.coroutines.flow.Flow

interface MonsterRepository {

    fun fetchMonsters(top: Int, skip: Int): Flow<FinishableResult>

    fun getMonsterListFlow(): Flow<Result<List<Monster>>>

    fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>>

    fun deleteMonster(id: Int): Flow<FinishableResult>

    fun deleteAllMonsters(): Flow<FinishableResult>
}
