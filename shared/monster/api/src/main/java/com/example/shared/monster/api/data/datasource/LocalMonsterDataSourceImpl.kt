package com.example.shared.monster.api.data.datasource

import com.example.shared.monster.api.data.database.MonsterDao
import com.example.shared.monster.api.data.database.toDbEntity
import com.example.shared.monster.api.domain.entity.FinishableResult
import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.monster.api.domain.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMonsterDataSourceImpl @Inject constructor(
    private val monsterDao: MonsterDao,
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
        monsterDao.deleteMonster(id)
        emit(Result.Finished)
    }

    override fun deleteAllMonsters(): Flow<Result<Unit>> = flow {
        emit(Result.Loading())
        monsterDao.deleteAllMonsters()
        emit(Result.Finished)
    }

    override fun saveMonsters(monsters: List<Monster>): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        monsterDao.insertAllMonsters(monsters.map { it.toDbEntity() })
        emit(Result.Finished)
    }
}
