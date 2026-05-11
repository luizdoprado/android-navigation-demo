package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

/**
 * Resolves an external URL to an internal deeplink URI string.
 * In production this would call an API to perform the URL resolution.
 * The returned URI is then passed to [InternalDeeplink.routeForUri] for navigation.
 */
interface DeeplinkResolver {
    fun resolve(url: String): String?
}

/**
 * Simulates the API-based URL resolution layer without making real network calls.
 * Maps external URL path segments to their corresponding internal deeplink URI.
 */
class SimulatedDeeplinkResolver : DeeplinkResolver {

    private companion object {
        private const val BASE = "android-app://example.app"
    }

    override fun resolve(url: String): String? {
        val segments = url
            .substringAfter("://")
            .substringAfter("/")
            .trimEnd('/')
            .split("/")
            .filter { it.isNotBlank() }

        return when {
            segments.isEmpty() -> null
            segments[0] == "home" -> "$BASE/home"
            segments[0] == "dashboard" -> "$BASE/dashboard"
            segments[0] == "notifications" -> "$BASE/notifications"
            segments[0] == "profile" -> "$BASE/profile"
            segments[0] == "details" && segments.size > 1 -> "$BASE/details/${segments[1]}"
            segments[0] == "login" -> "$BASE/login"
            else -> null
        }
    }
}
