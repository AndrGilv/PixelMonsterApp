package com.example.pixelmonsterapp3.data.database

import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.domain.entity.MonsterDiscoverer

@Entity(
    tableName = "monster_details",
    foreignKeys = [
        ForeignKey(
            entity = MonsterEntity::class,
            parentColumns = ["id"],
            childColumns = ["monster_id"],
            onDelete = CASCADE
        )
    ],
)
data class MonsterDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primary_id")
    val primaryId: Long,
    @NonNull
    @ColumnInfo(name = "monster_id")
    val monsterId: Int,
    @NonNull
    @Embedded(prefix = "discoverer")
    val discoveredBy: MonsterDiscoverer,
    @NonNull
    val experience: Int,
    @NonNull
    @ColumnInfo(name = "attribute_points")
    val attributePoints: Int,
    @NonNull
    @ColumnInfo(name = "max_health")
    val maxHealth: Int,
    @NonNull
    @ColumnInfo(name = "max_damage")
    val maxDamage: Double,
    @NonNull
    @ColumnInfo(name = "min_damage")
    val minDamage: Double,
    @NonNull
    @ColumnInfo(name = "critical_damage")
    val criticalDamage: Double,
    @NonNull
    @ColumnInfo(name = "hit_chance")
    val hitChance: Double,
    @NonNull
    @ColumnInfo(name = "dodge_chance")
    val dodgeChance: Double,
    @NonNull
    @ColumnInfo(name = "critical_chance")
    val criticalChance: Double,
)

data class MonsterWithDetails(
    @Embedded val monster: MonsterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "monster_id"
    )
    val monsterDetailsEntity: MonsterDetailsEntity,
) {
    fun toEntity() = MonsterDetails(
        monster = monster.toEntity(),
        discoveredBy = monsterDetailsEntity.discoveredBy,
        experience = monsterDetailsEntity.experience,
        attributePoints = monsterDetailsEntity.attributePoints,
        maxHealth = monsterDetailsEntity.maxHealth,
        maxDamage = monsterDetailsEntity.maxDamage,
        minDamage = monsterDetailsEntity.minDamage,
        criticalDamage = monsterDetailsEntity.criticalDamage,
        hitChance = monsterDetailsEntity.hitChance,
        dodgeChance = monsterDetailsEntity.dodgeChance,
        criticalChance = monsterDetailsEntity.criticalChance,
    )
}
