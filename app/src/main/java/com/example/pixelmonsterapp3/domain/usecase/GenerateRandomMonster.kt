package com.example.pixelmonsterapp3.domain.usecase

import com.example.pixelmonsterapp3.domain.entity.FinishableResult
import com.example.pixelmonsterapp3.domain.entity.Result
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateRandomMonster @Inject constructor(
    private val monsterRepository: MonsterRepository,
){

    operator fun invoke(): Flow<FinishableResult> =
        monsterRepository.generateRandomMonster()
}