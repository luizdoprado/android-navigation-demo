package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed class RegistrationRoute(override val route: String) : Route {
    @Serializable
    data object Registration : RegistrationRoute(route = "Registration")
}