package org.chorus_oss.protocol.core

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.UInt

object ProtoHelper {
    fun <T> serializeNullable(value: T?, stream: Sink, se: ProtoSerializer<T>) {
        Proto.Boolean.serialize(value != null, stream)
        if (value != null) {
            se.serialize(value, stream)
        }
    }

    fun <T> deserializeNullable(stream: Source, de: ProtoDeserializer<T>): T? {
        val present = Proto.Boolean.deserialize(stream)
        if (present) {
            return de.deserialize(stream)
        }
        return null
    }

    fun <T> serializeList(value: List<T>, stream: Sink, se: ProtoSerializer<T>) {
        ProtoVAR.UInt.serialize(value.size.toUInt(), stream)
        for (item in value) {
            se.serialize(item, stream)
        }
    }

    fun <T> deserializeList(stream: Source, de: ProtoDeserializer<T>): List<T> {
        val size = ProtoVAR.UInt.deserialize(stream).toInt()
        return List(size) {
            de.deserialize(stream)
        }
    }
}