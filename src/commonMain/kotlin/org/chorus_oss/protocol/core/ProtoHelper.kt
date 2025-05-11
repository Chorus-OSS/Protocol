package org.chorus_oss.protocol.core

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.UInt

object ProtoHelper {
    fun <T> serializeNullable(value: T?, stream: Sink, fn: (T, Sink) -> Unit) {
        Proto.Boolean.serialize(value != null, stream)
        if (value != null) {
            fn.invoke(value, stream)
        }
    }

    fun <T> deserializeNullable(stream: Source, fn: (Source) -> T): T? {
        val present = Proto.Boolean.deserialize(stream)
        if (present) {
            return fn.invoke(stream)
        }
        return null
    }

    fun <T> serializeList(value: List<T>, stream: Sink, fn: (T, Sink) -> Unit) {
        ProtoVAR.UInt.serialize(value.size.toUInt(), stream)
        for (item in value) {
            fn.invoke(item, stream)
        }
    }

    fun <T> deserializeList(stream: Source, fn: (Source) -> T): List<T> {
        val size = ProtoVAR.UInt.deserialize(stream).toInt()
        return List(size) {
            fn.invoke(stream)
        }
    }
}