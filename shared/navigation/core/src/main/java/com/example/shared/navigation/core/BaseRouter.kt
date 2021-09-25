package com.example.shared.navigation.core

abstract class BaseRouter(val navigator: Navigator) : Router {

    override fun navigateUp() = navigator.navigateUp()

    override fun navigateBack() = navigator.navigateBack()
}
