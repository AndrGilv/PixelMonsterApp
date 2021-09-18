package com.example.pixelmonsterapp3.data.network.response

import android.os.Parcelable
import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.MonsterAttributes
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.domain.entity.MonsterDiscoverer
import kotlinx.parcelize.Parcelize

@Parcelize
data class MonsterDetailsResponse(
    val id: Int,
    val locationId: Int,
    val element: String,
    val uniqueness: String,
    val encountered: Int,
    val level: Int,
    val attributes: MonsterAttributes,
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
) : Parcelable {

    fun toMonsterDetails() = MonsterDetails(
        monster = Monster(
            id = id,
            locationId = locationId,
            element = element,
            uniqueness = uniqueness,
            encountered = encountered,
            level = level,
            attributes = attributes,
        ),
        discoveredBy = discoveredBy,
        experience = experience,
        attributePoints = attributePoints,
        maxHealth = maxHealth,
        maxDamage = maxDamage,
        minDamage = minDamage,
        criticalDamage = criticalDamage,
        hitChance = hitChance,
        dodgeChance = dodgeChance,
        criticalChance = criticalChance,
    )
}
