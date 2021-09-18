package com.example.shared_monster_api.data.datasource

import com.example.shared_monster_api.domain.entity.Monster
import com.example.shared_monster_api.domain.entity.MonsterDetails
import com.example.shared_monster_api.domain.entity.Result
import kotlinx.coroutines.flow.Flow

interface RemoteMonsterDataSource {

    fun getMonsters(top: Int, skip: Int): Flow<Result<List<Monster>>>

    fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>>
}
