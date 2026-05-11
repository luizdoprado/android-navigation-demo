package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import androidx.core.net.toUri
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink.DeepLinkMatcher
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink.DeepLinkPattern
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink.DeepLinkRequest
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink.KeyDecoder
import kotlinx.serialization.serializer

interface InternalDeeplink {
    fun routeForUri(uri: String): AppRoute?
}

class InternalDeeplinkImpl : InternalDeeplink {

    private companion object {
        private const val BASE = "android-app://example.app"
    }

    /**
     * Registered deeplink patterns — one per supported URI template.
     * Path arguments use `{fieldName}` syntax matching the route's serializable field.
     * The order matters: more specific patterns should come before more general ones.
     */
    private val patterns = listOf(
        DeepLinkPattern(serializer<AppRoute.Home>(), "$BASE/home".toUri()),
        DeepLinkPattern(serializer<AppRoute.Dashboard>(), "$BASE/dashboard".toUri()),
        DeepLinkPattern(serializer<AppRoute.Notifications>(), "$BASE/notifications".toUri()),
        DeepLinkPattern(serializer<AppRoute.Profile>(), "$BASE/profile".toUri()),
        DeepLinkPattern(serializer<AppRoute.Details>(), "$BASE/details/{details}".toUri()),
        DeepLinkPattern(serializer<AppRoute.Login>(), "$BASE/login".toUri()),
        DeepLinkPattern(serializer<AppRoute.DeeplinkSimulator>(), "$BASE/deeplink-simulator".toUri()),
    )

    /**
     * STEP 1: Parse the incoming [uri] into a [DeepLinkRequest].
     * STEP 2: Try each registered [DeepLinkPattern] until a match is found.
     * STEP 3: Use [KeyDecoder] + kotlinx.serialization to decode the match args
     *         directly into the typed [AppRoute] — no manual when/startsWith needed.
     */
    override fun routeForUri(uri: String): AppRoute? {
        val request = DeepLinkRequest(uri.toUri())
        return patterns.firstNotNullOfOrNull { pattern ->
            DeepLinkMatcher(request, pattern).match()?.let { result ->
                KeyDecoder(result.args).decodeSerializableValue(result.serializer)
            }
        }
    }
}
