package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

/**
 * Handles navigation for a resolved [DeeplinkDestination].
 * Bridges the [DeeplinkDestination] domain model with the navigation layer.
 */
interface DeeplinkHandler {
    fun handle(destination: DeeplinkDestination)
}

class DeeplinkHandlerImpl(
    private val navigator: Navigator,
    private val internalDeeplink: InternalDeeplink
) : DeeplinkHandler {

    override fun handle(destination: DeeplinkDestination) {
        val uri = when (destination) {
            is DeeplinkDestination.Home -> internalDeeplink.toHome()
            is DeeplinkDestination.Dashboard -> internalDeeplink.toDashboard()
            is DeeplinkDestination.Notifications -> internalDeeplink.toNotifications()
            is DeeplinkDestination.Profile -> internalDeeplink.toProfile()
            is DeeplinkDestination.Details -> internalDeeplink.toDetails(destination.id)
            is DeeplinkDestination.Login -> internalDeeplink.toLogin()
        }
        navigator.navigate(uri)
    }
}
