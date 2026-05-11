package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink

import android.net.Uri
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.CompositeDecoder

/**
 * Represents a supported deeplink pattern backed by a [KSerializer] of the target [AppRoute].
 *
 * URI templates use `{argName}` placeholders for path arguments:
 *   e.g. `android-app://example.app/details/{details}`
 *
 * The placeholder name **must** match the corresponding field name in the serializable route.
 * One [DeepLinkPattern] is needed per supported URI template.
 */
internal class DeepLinkPattern<T : Any>(
    val serializer: KSerializer<T>,
    val uriPattern: Uri
) {
    private val templateArgRegex = Regex("\\{(.+?)\\}")

    /**
     * Ordered list of parsed path segments. Order matters for matching.
     */
    val pathSegments: List<PathSegment> = buildList {
        uriPattern.pathSegments.forEach { segment ->
            val match = templateArgRegex.find(segment)
            if (match != null) {
                val argName = match.groups[1]!!.value
                val elementIndex = serializer.descriptor.getElementIndex(argName)
                require(elementIndex != CompositeDecoder.UNKNOWN_NAME) {
                    "Path param '{$argName}' in $uriPattern not found in '${serializer.descriptor.serialName}'."
                }
                val kind = serializer.descriptor.getElementDescriptor(elementIndex).kind
                add(PathSegment(argName, isParam = true, parser = parserFor(kind)))
            } else {
                add(PathSegment(segment, isParam = false, parser = parserFor(PrimitiveKind.STRING)))
            }
        }
    }

    /**
     * Query parameter parsers keyed by parameter name.
     * Only parameters that exist on the serializable route are included.
     */
    val queryParsers: Map<String, ArgParser> = buildMap {
        uriPattern.queryParameterNames.forEach { name ->
            val idx = serializer.descriptor.getElementIndex(name)
            if (idx != CompositeDecoder.UNKNOWN_NAME) {
                this[name] = parserFor(serializer.descriptor.getElementDescriptor(idx).kind)
            }
        }
    }

    class PathSegment(
        val stringValue: String,
        val isParam: Boolean,
        val parser: ArgParser
    )
}

internal typealias ArgParser = (String) -> Any

private fun parserFor(kind: SerialKind): ArgParser = when (kind) {
    PrimitiveKind.STRING  -> { s -> s }
    PrimitiveKind.INT     -> { s -> s.toInt() }
    PrimitiveKind.LONG    -> { s -> s.toLong() }
    PrimitiveKind.BOOLEAN -> { s -> s.toBoolean() }
    PrimitiveKind.FLOAT   -> { s -> s.toFloat() }
    PrimitiveKind.DOUBLE  -> { s -> s.toDouble() }
    PrimitiveKind.BYTE    -> { s -> s.toByte() }
    PrimitiveKind.SHORT   -> { s -> s.toShort() }
    else -> throw IllegalArgumentException("Unsupported deeplink argument type: $kind")
}
