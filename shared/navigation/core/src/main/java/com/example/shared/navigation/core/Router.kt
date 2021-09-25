package com.example.shared.navigation.core

interface Router {
    fun navigateUp(): Boolean
    fun navigateBack(): Boolean
}
