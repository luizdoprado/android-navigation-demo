package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
    @Serializable data object Home : AppRoute
    @Serializable data object Dashboard : AppRoute
    @Serializable data object Notifications : AppRoute
    @Serializable data object Profile : AppRoute
    @Serializable data class Details(val details: String) : AppRoute
    @Serializable data object Login : AppRoute
    @Serializable data object DeeplinkSimulator : AppRoute
}
