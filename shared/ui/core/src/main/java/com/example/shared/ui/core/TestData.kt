package com.example.shared.ui.core

import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.entity.MonsterAttributes
import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.monster.api.domain.entity.MonsterDiscoverer
import com.example.shared.ui.core.presentation.toParcelableList

object TestData {

    val testMonsterAttributes = MonsterAttributes(
        strength = 100500,
        dexterity = 256,
        endurance = 123,
        intelligence = 1,
    )

    val testMonster = Monster(
        id = 777,
        locationId = 888,
        element = "адамантий",
        uniqueness = "легендарный",
        encountered = 0,
        level = 9001,
        attributes = testMonsterAttributes
    )

    val testMonsterList = (0..30).map { testMonster }.toParcelableList()

    val testDiscoverer = MonsterDiscoverer(
        id = "123-456-789-012",
        name = "qweшник",
    )

    val testMonsterDetails = MonsterDetails(
        monster = testMonster,
        discoveredBy = testDiscoverer,
        experience = 987,
        attributePoints = 456,
        maxHealth = 100,
        maxDamage = 59.3,
        minDamage = 23.9,
        criticalDamage = 198.8,
        hitChance = 0.8,
        dodgeChance = 0.4,
        criticalChance = 0.5,
    )
}
