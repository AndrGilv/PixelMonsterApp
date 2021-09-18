package com.example.pixelmonsterapp3.data.datasource

import com.example.pixelmonsterapp3.data.database.MonsterDao
import com.example.pixelmonsterapp3.data.database.toDbEntity
import com.example.pixelmonsterapp3.di.annotations.IoCoroutineScope
import com.example.pixelmonsterapp3.domain.entity.FinishableResult
import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.domain.entity.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMonsterDataSourceImpl @Inject constructor(
    private val monsterDao: MonsterDao,
    @IoCoroutineScope private val coroutineScope: CoroutineScope,
) : LocalMonsterDataSource {

    override fun getSavedMonsterListFlow(): Flow<Result<List<Monster>>> =
        monsterDao.getMonsters().distinctUntilChanged().map { monsterList ->
            Result.Success(monsterList.map { it.toEntity() })
        }

    override fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>> = flow {
        emit(Result.Loading())
        emit(Result.Success(monsterDao.getMonsterWithDetails(id).toEntity()))
    }

    override fun deleteMonster(id: Int): Flow<Result<Unit>> = flow {
        emit(Result.Loading())
        coroutineScope.launch {
            monsterDao.deleteMonster(id)
            emit(Result.Finished)
        }
    }

    override fun deleteAllMonsters(): Flow<Result<Unit>> = flow {
        emit(Result.Loading())
        coroutineScope.launch {
            monsterDao.deleteAllMonsters()
            emit(Result.Finished)
        }
    }

    override fun saveMonsters(monsters: List<Monster>): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        monsterDao.insertAllMonsters(monsters.map { it.toDbEntity() })
        emit(Result.Finished)
    }
}
