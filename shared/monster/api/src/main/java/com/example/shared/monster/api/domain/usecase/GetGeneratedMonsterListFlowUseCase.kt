package com.example.shared.monster.api.domain.usecase

import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.entity.Result
import com.example.shared.monster.api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  () -> Flow<Result<List< Monster >>>
 * **/
@Singleton
class GetGeneratedMonsterListFlowUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
) {
    operator fun invoke(): Flow<Result<List<Monster>>> =
        monstersRepository.getMonsterListFlow()
}
