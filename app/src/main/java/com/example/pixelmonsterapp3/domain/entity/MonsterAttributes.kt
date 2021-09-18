package com.example.pixelmonsterapp3.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
class MonsterAttributes(
    val strength: Int,
    val dexterity: Int,
    val endurance: Int,
    val intelligence: Int,
) : Parcelable{

    companion object {
        fun random() =
            MonsterAttributes(
                strength = Random.nextInt(1000),
                dexterity = Random.nextInt(1000),
                endurance = Random.nextInt(1000),
                intelligence = Random.nextInt(1000),
            )
    }
}
