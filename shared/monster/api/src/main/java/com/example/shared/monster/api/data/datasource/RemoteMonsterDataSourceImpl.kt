package com.example.shared.monster.api.data.datasource

import com.example.shared.monster.api.data.network.MonsterApi
import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.monster.api.domain.entity.Result
import com.example.shared.monster.api.runApiCall
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
