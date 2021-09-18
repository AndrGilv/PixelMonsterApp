package com.example.shared_monster_api.domain.usecase

import com.example.shared_monster_api.domain.entity.FinishableResult
import com.example.shared_monster_api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  () -> Flow<Result< Unit >>
 * **/
class DeleteAllMonstersUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
){
    operator fun invoke(): Flow<FinishableResult> =
        monstersRepository.deleteAllMonsters()
}
