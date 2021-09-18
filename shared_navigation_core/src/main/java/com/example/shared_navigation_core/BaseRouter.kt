package com.example.shared_navigation_core

abstract class BaseRouter(val navigator: Navigator) : Router {

    override fun navigateUp() = navigator.navigateUp()

    override fun navigateBack() = navigator.navigateBack()
}
