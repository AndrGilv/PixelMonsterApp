package com.example.shared.monster.api.domain.usecase

import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.monster.api.domain.entity.Result
import com.example.shared.monster.api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  (id: Int) -> Flow<Result< MonsterDetails >>
 * **/
@Singleton
class GetMonsterDetailsUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
) {
    operator fun invoke(id: Int): Flow<Result<MonsterDetails>> =
        monstersRepository.getMonsterDetails(id)
}
