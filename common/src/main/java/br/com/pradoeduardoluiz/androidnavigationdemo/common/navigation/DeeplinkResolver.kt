package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

/**
 * Resolves an external URL to an internal [DeeplinkDestination].
 * In production this would call an API to perform the URL resolution.
 */
interface DeeplinkResolver {
    fun resolve(url: String): DeeplinkDestination?
}

/**
 * Simulates the API-based URL resolution layer without making real network calls.
 * Maps URL path segments to their corresponding [DeeplinkDestination].
 */
class SimulatedDeeplinkResolver : DeeplinkResolver {

    override fun resolve(url: String): DeeplinkDestination? {
        val segments = url
            .substringAfter("://")
            .substringAfter("/")
            .trimEnd('/')
            .split("/")
            .filter { it.isNotBlank() }

        return when {
            segments.isEmpty() -> null
            segments[0] == "home" -> DeeplinkDestination.Home
            segments[0] == "dashboard" -> DeeplinkDestination.Dashboard
            segments[0] == "notifications" -> DeeplinkDestination.Notifications
            segments[0] == "profile" -> DeeplinkDestination.Profile
            segments[0] == "details" && segments.size > 1 -> DeeplinkDestination.Details(segments[1])
            segments[0] == "login" -> DeeplinkDestination.Login
            else -> null
        }
    }
}
