package com.example.shared_monster_api.data.network.response

import com.example.shared_monster_api.domain.entity.Monster

data class MonsterResponse(
    val value: List<Monster>,
)
