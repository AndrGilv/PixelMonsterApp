package com.example.shared.monster.api.domain.usecase

import com.example.shared.monster.api.domain.entity.FinishableResult
import com.example.shared.monster.api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  (id: Int) -> Flow<Result< Unit >>
 * **/
@Singleton
class DeleteMonsterUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
) {
    operator fun invoke(id: Int): Flow<FinishableResult> =
        monstersRepository.deleteMonster(id)
}
