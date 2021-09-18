package com.example.shared_monster_api.data.datasource

import com.example.shared_monster_api.data.network.MonsterApi
import com.example.shared_monster_api.domain.entity.Monster
import com.example.shared_monster_api.domain.entity.MonsterDetails
import com.example.shared_monster_api.domain.entity.Result
import com.example.shared_monster_api.runApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteMonsterDataSourceImpl @Inject constructor(
    private val api: MonsterApi,
) : RemoteMonsterDataSource {

    override fun getMonsters(top: Int, skip: Int): Flow<Result<List<Monster>>> = runApiCall(
        toEntity = {
            this.value
        },
        query = { api.getMonsters(top, skip) }
    )

    override fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>> = runApiCall(
        toEntity = { this.toMonsterDetails() },
        query = { api.getMonsterDetails(id) }
    )
}
