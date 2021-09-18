package com.example.pixelmonsterapp3.data.datasource

import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.domain.entity.Result
import kotlinx.coroutines.flow.Flow

interface RemoteMonsterDataSource {

    fun getMonsters(top: Int, skip: Int): Flow<Result<List<Monster>>>

    fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>>
}
