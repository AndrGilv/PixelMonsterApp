package com.example.shared.monster.api.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class MonsterDetails(
    val monster: Monster,
    val discoveredBy: MonsterDiscoverer,
    val experience: Int,
    val attributePoints: Int,
    val maxHealth: Int,
    val maxDamage: Double,
    val minDamage: Double,
    val criticalDamage: Double,
    val hitChance: Double,
    val dodgeChance: Double,
    val criticalChance: Double,
) : Parcelable, MonsterInterface by monster {

    companion object {
        fun random() = MonsterDetails(
            monster = Monster.random(),
            discoveredBy = MonsterDiscoverer.random(),
            experience = Random.nextInt(100500),
            attributePoints = Random.nextInt(100500),
            maxHealth = Random.nextInt(100),
            maxDamage = Random.nextDouble(500.0, 1000.0),
            minDamage = Random.nextDouble(500.0),
            criticalDamage = Random.nextDouble(1000.0, 2000.0),
            hitChance = Random.nextDouble(1.0),
            dodgeChance = Random.nextDouble(1.0),
            criticalChance = Random.nextDouble(1.0),
        )

        fun randomList(size: Int = 10) = (1..size).map { random() }
    }
}
