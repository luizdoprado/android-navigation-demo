package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.deeplink

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

/**
 * Decodes a [Map] of argument name → value into a `@Serializable` route object.
 *
 * Uses kotlinx.serialization's [AbstractDecoder] to drive deserialization from
 * the typed args map produced by [DeepLinkMatcher], avoiding any JSON overhead.
 *
 * **Assumption:** all argument types are primitives (String, Int, Boolean, etc.).
 */
@OptIn(ExperimentalSerializationApi::class)
internal class KeyDecoder(
    private val arguments: Map<String, Any>
) : AbstractDecoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()

    private var elementIndex = -1
    private var elementName = ""

    /**
     * Sequentially returns the index of each element that has a matching value in [arguments].
     * Elements with no value in the map are skipped (supports optional/nullable fields).
     */
    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        while (true) {
            elementIndex++
            if (elementIndex >= descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
            elementName = descriptor.getElementName(elementIndex)
            if (arguments.containsKey(elementName)) return elementIndex
        }
    }

    override fun decodeValue(): Any {
        return checkNotNull(arguments[elementName]) {
            "Unexpected null value for non-nullable argument '$elementName'"
        }
    }

    override fun decodeNull(): Nothing? = null

    override fun decodeNotNullMark(): Boolean = arguments[elementName] != null
}
