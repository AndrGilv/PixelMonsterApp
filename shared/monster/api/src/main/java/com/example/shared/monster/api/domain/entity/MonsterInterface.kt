package com.example.shared.monster.api.domain.entity

interface MonsterInterface {
    val id: Int
    val locationId: Int
    val element: String
    val uniqueness: String
    val encountered: Int
    val level: Int
    val attributes: MonsterAttributes
}
