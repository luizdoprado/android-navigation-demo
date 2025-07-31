package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed class LoginRoute(override val route: String) : Route {
    @Serializable
    data object Login : LoginRoute(route = "Login")
}