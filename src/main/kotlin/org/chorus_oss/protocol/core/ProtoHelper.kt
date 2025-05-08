package org.chorus_oss.protocol.core

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.types.Boolean

object ProtoHelper {
    fun <T> writeNullable(value: T?, stream: Buffer, fn: (T, Buffer) -> Unit) {
        Proto.Boolean.serialize(value != null, stream)
        if (value != null) {
            fn.invoke(value, stream)
        }
    }

    fun <T> readNullable(stream: Buffer, fn: (Buffer) -> T): T? {
        val present = Proto.Boolean.deserialize(stream)
        if (present) {
            return fn.invoke(stream)
        }
        return null
    }
}