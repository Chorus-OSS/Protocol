package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.types.ActorUniqueID


data class DebugInfoPacket(
    val playerUniqueID: ActorUniqueID,
    val data: ByteString
) : Packet(id) {
    companion object : PacketCodec<DebugInfoPacket> {
        override val id: Int = 155

        override fun serialize(value: DebugInfoPacket, stream: Sink) {
            ActorUniqueID.serialize(value.playerUniqueID, stream)
            Proto.ByteString.serialize(value.data, stream)
        }

        override fun deserialize(stream: Source): DebugInfoPacket {
            return DebugInfoPacket(
                playerUniqueID = ActorUniqueID.deserialize(stream),
                data = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
