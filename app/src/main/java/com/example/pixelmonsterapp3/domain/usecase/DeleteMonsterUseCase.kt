package com.example.pixelmonsterapp3.domain.usecase

import com.example.pixelmonsterapp3.domain.entity.*
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  (id: Int) -> Flow<Result< Unit >>
 * **/
class DeleteMonsterUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
){
    operator fun invoke(id: Int): Flow<FinishableResult> =
        monstersRepository.deleteMonster(id)
}