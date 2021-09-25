package com.example.shared.monster.api.data.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.entity.MonsterAttributes
import java.time.LocalDateTime

@Entity(tableName = "monster")
data class MonsterEntity(
    @PrimaryKey
    val id: Int,
    @NonNull
    @ColumnInfo(name = "location_id")
    val locationId: Int,
    @NonNull
    val element: String,
    @NonNull
    val uniqueness: String,
    @NonNull
    val encountered: Int,
    @NonNull
    val level: Int,
    @Embedded
    @NonNull
    val attributes: MonsterAttributes,
    @NonNull
    @ColumnInfo(name = "creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now(),
) {

    fun toEntity() = Monster(
        id = id,
        locationId = locationId,
        element = element,
        uniqueness = uniqueness,
        encountered = encountered,
        level = level,
        attributes = attributes,
    )
}

fun Monster.toDbEntity() = MonsterEntity(
    id = id,
    locationId = locationId,
    element = element,
    uniqueness = uniqueness,
    encountered = encountered,
    level = level,
    attributes = attributes
)
