package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.DebugShape

data class ServerScriptDebugDrawerPacket(
    val shapes: List<DebugShape>
) : Packet(id) {
    companion object : PacketCodec<ServerScriptDebugDrawerPacket> {
        override val id: Int
            get() = 328

        override fun serialize(
            value: ServerScriptDebugDrawerPacket,
            stream: Sink
        ) {
            ProtoHelper.serializeList(value.shapes, stream, DebugShape)
        }

        override fun deserialize(stream: Source): ServerScriptDebugDrawerPacket {
            return ServerScriptDebugDrawerPacket(
                shapes = ProtoHelper.deserializeList(stream, DebugShape)
            )
        }
    }
}
