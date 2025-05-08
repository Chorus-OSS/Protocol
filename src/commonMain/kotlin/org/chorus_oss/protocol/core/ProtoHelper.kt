package org.chorus_oss.protocol.core

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.UInt

object ProtoHelper {
    fun <T> serializeNullable(value: T?, stream: Buffer, fn: (T, Buffer) -> Unit) {
        Proto.Boolean.serialize(value != null, stream)
        if (value != null) {
            fn.invoke(value, stream)
        }
    }

    fun <T> deserializeNullable(stream: Buffer, fn: (Buffer) -> T): T? {
        val present = Proto.Boolean.deserialize(stream)
        if (present) {
            return fn.invoke(stream)
        }
        return null
    }

    fun <T> serializeList(value: List<T>, stream: Buffer, fn: (T, Buffer) -> Unit) {
        ProtoVAR.UInt.serialize(value.size.toUInt(), stream)
        for (item in value) {
            fn.invoke(item, stream)
        }
    }

    fun <T> deserializeList(stream: Buffer, fn: (Buffer) -> T): List<T> {
        val size = ProtoVAR.UInt.deserialize(stream).toInt()
        return List(size) {
            fn.invoke(stream)
        }
    }
}