package com.example.shared_monster_api.data.repository

import com.example.shared_monster_api.domain.entity.FinishableResult
import com.example.shared_monster_api.domain.entity.Monster
import com.example.shared_monster_api.domain.entity.MonsterDetails
import com.example.shared_monster_api.domain.entity.Result
import com.example.shared_monster_api.domain.repository.MonsterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockMonsterRepository @Inject constructor() : MonsterRepository {

    private val monsterDetailsList = MonsterDetails.randomList(20).toMutableList()
    private val monsterListFlow: MutableStateFlow<List<MonsterDetails>> =
        MutableStateFlow(monsterDetailsList)

    override fun fetchMonsters(top: Int, skip: Int): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        monsterDetailsList.add(MonsterDetails.random())
        delay(1000)
        monsterListFlow.value = monsterDetailsList
        emit(Result.Finished)
    }

    override fun getMonsterListFlow(): Flow<Result<List<Monster>>> = flow {
        emit(Result.Loading())
        monsterListFlow.collect {
            emit(Result.Success(it.map { it.monster }))
        }
    }

    override fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>> =
        MutableStateFlow<Result<MonsterDetails>>(
            Result.Loading()).apply {
            CoroutineScope(Dispatchers.IO).launch {
                delay(1000)
                value = Result.Success(monsterDetailsList.first { it.id == id })
            }
        }

    override fun deleteMonster(id: Int): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        monsterDetailsList.removeAll { it.id == id }
        monsterListFlow.value = monsterDetailsList
        emit(Result.Finished)
    }

    override fun deleteAllMonsters(): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        monsterDetailsList.clear()
        monsterListFlow.value = monsterDetailsList
        emit(Result.Finished)
    }
}
