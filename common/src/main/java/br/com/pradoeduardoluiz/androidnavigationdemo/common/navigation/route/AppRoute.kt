package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute(override val route: String) : Route {
    @Serializable
    data object Home : AppRoute(route = "Home")

    @Serializable
    object Profile : AppRoute(route = "Profile")

    @Serializable
    data object Notifications : AppRoute(route = "Notifications")

    @Serializable
    data object Dashboard : AppRoute(route = "Dashboard")

    @Serializable
    data class Details(val details: String) : AppRoute(route = "Details")

    @Serializable
    data class ComposeHost(val composeRoute: String) : AppRoute(route = "Compose")
}

@Serializable
sealed class AppComposeRoute(override val route: String) : Route {
    @Serializable
    data object FirstCompose : AppComposeRoute(route = "FirstCompose")

    @Serializable
    data object SecondCompose : AppComposeRoute(route = "SecondCompose")
}
