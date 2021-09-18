package com.example.shared_monster_api.domain.usecase

import com.example.shared_monster_api.domain.entity.MonsterDetails
import com.example.shared_monster_api.domain.entity.Result
import com.example.shared_monster_api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  (id: Int) -> Flow<Result< MonsterDetails >>
 * **/
class GetMonsterDetailsUseCase @Inject constructor(
    private val monstersRepository: MonsterRepository,
){
    operator fun invoke(id: Int): Flow<Result<MonsterDetails>> =
        monstersRepository.getMonsterDetails(id)
}
