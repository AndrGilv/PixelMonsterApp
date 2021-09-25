package com.example.shared.monster.api.domain.repository

import com.example.shared.monster.api.domain.entity.FinishableResult
import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.monster.api.domain.entity.Result
import kotlinx.coroutines.flow.Flow

interface MonsterRepository {

    fun fetchMonsters(top: Int, skip: Int): Flow<FinishableResult>

    fun getMonsterListFlow(): Flow<Result<List<Monster>>>

    fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>>

    fun deleteMonster(id: Int): Flow<FinishableResult>

    fun deleteAllMonsters(): Flow<FinishableResult>
}
