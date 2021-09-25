package com.example.shared.monster.api.domain.usecase

import com.example.shared.monster.api.domain.entity.FinishableResult
import com.example.shared.monster.api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  () -> Flow<Result< Unit >>
 * **/
class DeleteAllMonstersUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
) {
    operator fun invoke(): Flow<FinishableResult> =
        monstersRepository.deleteAllMonsters()
}
