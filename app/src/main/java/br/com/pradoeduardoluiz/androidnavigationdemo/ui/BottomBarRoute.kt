package br.com.pradoeduardoluiz.androidnavigationdemo.ui

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class BottomBarRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data object Dashboard : Route()

    @Serializable
    data object Notifications : Route()

    @Serializable
    data object Login : Route()

    @Serializable
    data class Profile(val userId: String) : Route()

    @Serializable
    data class Details(val userId: String) : Route()
}