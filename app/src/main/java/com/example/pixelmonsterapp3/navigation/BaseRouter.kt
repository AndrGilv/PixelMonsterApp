package com.example.pixelmonsterapp3.navigation

abstract class BaseRouter(val navigator: Navigator) : Router {

    override fun navigateUp() = navigator.navigateUp()

    override fun navigateBack() = navigator.navigateBack()
}

interface Router {
    fun navigateUp(): Boolean
    fun navigateBack(): Boolean
}