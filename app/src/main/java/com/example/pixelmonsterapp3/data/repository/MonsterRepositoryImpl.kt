package com.example.pixelmonsterapp3.data.repository

import com.example.loanapp.di.annotations.LocalDataSource
import com.example.loanapp.di.annotations.RemoteDataSource
import com.example.pixelmonsterapp3.TestData.testMonster
import com.example.pixelmonsterapp3.TestData.testMonsterDetails
import com.example.pixelmonsterapp3.data.datasource.MonsterDataSource
import com.example.pixelmonsterapp3.domain.entity.*
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine
import kotlin.time.*

@Singleton
class MonsterRepositoryImpl @Inject constructor(
    @LocalDataSource private val localDataSource: MonsterDataSource,
    @RemoteDataSource private val remoteDataSource: MonsterDataSource,
) : MonsterRepository {

    private val monsterDetailsList = MonsterDetails.randomList(20).toMutableList()
    private val monsterListFlow:MutableStateFlow<List<MonsterDetails>>  = MutableStateFlow(monsterDetailsList)

    override fun generateRandomMonster(): Flow<FinishableResult> {
        return channelFlow {
            send(Result.Loading())
            monsterDetailsList.add(MonsterDetails.random())
            monsterListFlow.value = monsterDetailsList
            send(Result.Finished)
        }
    }

    override fun getGeneratedMonsterListFlow(): Flow<Result<List<Monster>>> {
        return channelFlow {
            send(Result.Loading())
            monsterListFlow.collect {
                send(Result.Success(it.map { it.monster }))
            }
        }
    }

    override fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>> {
        return MutableStateFlow<Result<MonsterDetails>>(Result.Loading()).apply {
            GlobalScope.launch {
                delay(1000)
                value = Result.Success(monsterDetailsList.first { it.id == id })
            }
        }
    }

    override fun deleteMonster(id: Int): Flow<FinishableResult> {
        return channelFlow {
            send(Result.Loading())
            monsterDetailsList.removeIf { it.id == id }
            monsterListFlow.value = monsterDetailsList
            send(Result.Finished)
        }
    }

    override fun deleteAllMonsters(): Flow<FinishableResult> {
        return channelFlow {
            send(Result.Loading())
            monsterDetailsList.clear()
            monsterListFlow.value = monsterDetailsList
            send(Result.Finished)
        }
    }
}