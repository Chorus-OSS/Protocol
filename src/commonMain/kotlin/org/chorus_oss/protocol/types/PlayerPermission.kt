package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class PlayerPermission {
    Visitor,
    Member,
    Operator,
    Custom;

    companion object : ProtoCodec<PlayerPermission> {
        override fun serialize(value: PlayerPermission, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): PlayerPermission {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
