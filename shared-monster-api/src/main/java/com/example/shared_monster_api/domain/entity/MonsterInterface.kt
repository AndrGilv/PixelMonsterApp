package com.example.shared_monster_api.domain.entity

interface MonsterInterface {
    val id: Int
    val locationId: Int
    val element: String
    val uniqueness: String
    val encountered: Int
    val level: Int
    val attributes: MonsterAttributes
}
