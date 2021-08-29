package com.example.pixelmonsterapp3.data.datasource

import com.example.loanapp.di.annotations.RemoteDataSource
import com.example.pixelmonsterapp3.domain.entity.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@RemoteDataSource
class RemoteMonsterDataSourceImpl @Inject constructor(

) : MonsterDataSource{

    override fun getRandomMonster(): Flow<Result<Monster>> {
        TODO("Not yet implemented")
    }

    override fun getGeneratedMonsterListFlow(): Flow<Result<List<Monster>>> {
        TODO("Not yet implemented")
    }

    override fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>> {
        TODO("Not yet implemented")
    }

    override fun deleteMonster(id: Int): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteAllMonsters(): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}