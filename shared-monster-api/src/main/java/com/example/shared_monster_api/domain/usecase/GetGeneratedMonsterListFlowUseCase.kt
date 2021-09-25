package com.example.shared_monster_api.domain.usecase

import com.example.shared_monster_api.domain.entity.Monster
import com.example.shared_monster_api.domain.entity.Result
import com.example.shared_monster_api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  () -> Flow<Result<List< Monster >>>
 * **/
class GetGeneratedMonsterListFlowUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
) {
    operator fun invoke(): Flow<Result<List<Monster>>> =
        monstersRepository.getMonsterListFlow()
}
