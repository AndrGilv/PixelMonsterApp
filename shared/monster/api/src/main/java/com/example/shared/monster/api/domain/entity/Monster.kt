package com.example.shared.monster.api.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Monster(
    override val id: Int,
    override val locationId: Int,
    override val element: String,
    override val uniqueness: String,
    override val encountered: Int,
    override val level: Int,
    override val attributes: MonsterAttributes,
) : Parcelable, MonsterInterface {

    companion object {
        private val testElements = listOf("water", "fire", "air", "earth")
        private val uniquenessList = listOf("legendary", "ordinary", "unique")

        fun random() = Monster(
            id = Random.nextInt(999_999),
            locationId = Random.nextInt(999_999),
            element = testElements.random(),
            uniqueness = uniquenessList.random(),
            encountered = Random.nextInt(100),
            level = Random.nextInt(9001),
            attributes = MonsterAttributes.random(),
        )

        fun randomList(size: Int = 10) = (1..size).map { random() }
    }
}
