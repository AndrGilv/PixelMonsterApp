package com.example.shared.monster.api.data.datasource

import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.monster.api.domain.entity.Result
import kotlinx.coroutines.flow.Flow

interface RemoteMonsterDataSource {

    fun getMonsters(top: Int, skip: Int): Flow<Result<List<Monster>>>

    fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>>
}
