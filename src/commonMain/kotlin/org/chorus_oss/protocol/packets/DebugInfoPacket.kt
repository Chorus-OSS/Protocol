package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.types.ActorUniqueID


data class DebugInfoPacket(
    val playerUniqueID: ActorUniqueID,
    val data: ByteString
) : Packet(id) {
    companion object : PacketCodec<DebugInfoPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.DEBUG_INFO_PACKET

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
