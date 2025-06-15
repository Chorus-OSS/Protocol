package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.Vector3f


data class ServerPostMovePositionPacket(
    val position: Vector3f
) : Packet(id) {
    companion object : PacketCodec<ServerPostMovePositionPacket> {
        override val id: Int = 16

        override fun serialize(
            value: ServerPostMovePositionPacket,
            stream: Sink
        ) {
            Vector3f.serialize(value.position, stream)
        }

        override fun deserialize(stream: Source): ServerPostMovePositionPacket {
            return ServerPostMovePositionPacket(
                position = Vector3f.deserialize(stream)
            )
        }
    }
}
