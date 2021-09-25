package com.example.shared.monster.api.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
class MonsterDiscoverer(
    val id: String,
    val name: String,
) : Parcelable {

    companion object {
        fun random() = MonsterDiscoverer(
            id = Random.nextLong(1_000_000).toString(),
            name = "some discoverer"
        )
    }
}
