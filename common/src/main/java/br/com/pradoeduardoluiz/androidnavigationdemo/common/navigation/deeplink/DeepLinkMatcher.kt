package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink

import android.util.Log
import kotlinx.serialization.KSerializer

private const val TAG = "DeepLinkMatcher"

/**
 * Compares a [DeepLinkRequest] against a [DeepLinkPattern] and returns a
 * [DeepLinkMatchResult] when they match, or null otherwise.
 *
 * Matching rules:
 * - scheme and authority must be equal (case-insensitive authority)
 * - path segment count must match
 * - static path segments must match exactly
 * - `{param}` path segments are extracted as typed arguments
 * - query parameters defined in the pattern are extracted if present in the request
 */
internal class DeepLinkMatcher<T : Any>(
    private val request: DeepLinkRequest,
    private val pattern: DeepLinkPattern<T>
) {
    fun match(): DeepLinkMatchResult<T>? {
        if (request.uri.scheme != pattern.uriPattern.scheme) return null
        if (!request.uri.authority.equals(pattern.uriPattern.authority, ignoreCase = true)) return null
        if (request.pathSegments.size != pattern.pathSegments.size) return null

        // Fast path: exact URI match (no params)
        if (request.uri == pattern.uriPattern) return DeepLinkMatchResult(pattern.serializer, emptyMap())

        val args = mutableMapOf<String, Any>()

        // Match path segments in order
        request.pathSegments
            .asSequence()
            .zip(pattern.pathSegments.asSequence())
            .forEach { (requested, candidate) ->
                if (candidate.isParam) {
                    val parsed = try {
                        candidate.parser(requested)
                    } catch (e: Exception) {
                        Log.w(TAG, "Failed to parse path arg '${candidate.stringValue}' value '$requested'", e)
                        return null
                    }
                    args[candidate.stringValue] = parsed
                } else if (requested != candidate.stringValue) {
                    return null
                }
            }

        // Extract declared query parameters
        request.queries.forEach { (name, value) ->
            val parser = pattern.queryParsers[name] ?: return@forEach
            val parsed = try {
                parser(value)
            } catch (e: Exception) {
                Log.w(TAG, "Failed to parse query param '$name' value '$value'", e)
                return null
            }
            args[name] = parsed
        }

        return DeepLinkMatchResult(pattern.serializer, args)
    }
}

internal data class DeepLinkMatchResult<T : Any>(
    val serializer: KSerializer<T>,
    val args: Map<String, Any>
)
