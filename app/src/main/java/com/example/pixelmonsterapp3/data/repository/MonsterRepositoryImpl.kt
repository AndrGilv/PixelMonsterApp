package com.example.pixelmonsterapp3.data.repository

import com.example.pixelmonsterapp3.data.datasource.LocalMonsterDataSource
import com.example.pixelmonsterapp3.data.datasource.RemoteMonsterDataSource
import com.example.pixelmonsterapp3.domain.entity.FinishableResult
import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.domain.entity.Result
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonsterRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMonsterDataSource,
    private val remoteDataSource: RemoteMonsterDataSource,
) : MonsterRepository {

    override fun fetchMonsters(top: Int, skip: Int): Flow<FinishableResult> = handleFinisable(
        inputFlow = remoteDataSource.getMonsters(top = top, skip = skip)
    ) { result ->
        localDataSource.saveMonsters(result)
    }

    override fun getMonsterListFlow(): Flow<Result<List<Monster>>> =
        localDataSource.getSavedMonsterListFlow()

    override fun getMonsterDetails(id: Int): Flow<Result<MonsterDetails>> = flow {
        emit(Result.Loading())

        emitAll(remoteDataSource.getMonsterDetails(id))
        // TODO: сделать так чтобы одновременно шёл запрос на бд и сервер и где быстрее нашлось от туда и бралось
    }

    override fun deleteMonster(id: Int): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        emitAll(localDataSource.deleteMonster(id))
    }

    override fun deleteAllMonsters(): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        emitAll(localDataSource.deleteAllMonsters())
    }

    private fun <InputResult> handleFinisable(
        inputFlow: Flow<Result<InputResult>>,
        onSuccess: (value: InputResult) -> Flow<FinishableResult>,
    ): Flow<FinishableResult> = flow {
        emit(Result.Loading())
        inputFlow.collect { result ->
            when (result) {
                is Result.Success -> {
                    emitAll(onSuccess(result.value))
                }
                is Result.Loading -> emit(Result.Loading<Unit>())
                is Result.Error -> emit(Result.Error<Unit>(result.exception, result.message))
                is Result.Failure -> emit(Result.Failure<Unit>(result.message))
            }
        }
    }
}
