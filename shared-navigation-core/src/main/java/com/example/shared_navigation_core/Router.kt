package com.example.shared_navigation_core

interface Router {
    fun navigateUp(): Boolean
    fun navigateBack(): Boolean
}
