package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink

import android.net.Uri

/**
 * Parses an incoming deeplink [Uri] into an easily readable format
 * with separate path segments and query parameters.
 */
internal class DeepLinkRequest(val uri: Uri) {
    val pathSegments: List<String> = uri.pathSegments

    val queries: Map<String, String> = buildMap {
        uri.queryParameterNames.forEach { name ->
            this[name] = uri.getQueryParameter(name)!!
        }
    }
}
