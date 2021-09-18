package com.example.pixelmonsterapp3.data.datasource

import com.example.pixelmonsterapp3.data.network.MonsterApi
import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.domain.entity.Result
import com.example.pixelmonsterapp3.runApiCall
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
