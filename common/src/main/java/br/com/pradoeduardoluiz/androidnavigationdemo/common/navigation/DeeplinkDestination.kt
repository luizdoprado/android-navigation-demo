package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

sealed class DeeplinkDestination {
    object Home : DeeplinkDestination()
    object Dashboard : DeeplinkDestination()
    object Notifications : DeeplinkDestination()
    object Profile : DeeplinkDestination()
    data class Details(val id: String) : DeeplinkDestination()
    object Login : DeeplinkDestination()
}
