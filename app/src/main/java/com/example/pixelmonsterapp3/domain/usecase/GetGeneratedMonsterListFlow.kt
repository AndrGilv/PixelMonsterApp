package com.example.pixelmonsterapp3.domain.usecase

import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.Result
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGeneratedMonsterListFlow @Inject constructor(
    private val monstersRepository: MonsterRepository,
){
    operator fun invoke(): Flow<Result<List<Monster>>> =
        monstersRepository.getGeneratedMonsterListFlow()
}