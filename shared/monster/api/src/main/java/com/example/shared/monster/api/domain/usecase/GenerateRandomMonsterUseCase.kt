package com.example.shared.monster.api.domain.usecase

import com.example.shared.monster.api.domain.entity.FinishableResult
import com.example.shared.monster.api.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

/**
 *  () -> Flow<Result< Unit >>
 * **/
class GenerateRandomMonsterUseCase @Inject constructor(
    private val monsterRepository: MonsterRepository,
) {

    companion object {
        const val MAX_SKIP = 2250
    }

    operator fun invoke(): Flow<FinishableResult> =
        monsterRepository.fetchMonsters(top = 1, skip = Random.nextInt(0, MAX_SKIP))
}
