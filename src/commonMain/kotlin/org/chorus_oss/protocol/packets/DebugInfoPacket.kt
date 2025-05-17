package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorUniqueID


data class DebugInfoPacket(
    val playerUniqueID: ActorUniqueID,
    val data: List<Byte>
) : Packet(id) {
    companion object : PacketCodec<DebugInfoPacket> {
        override val id: Int
            get() = ProtocolInfo.DEBUG_INFO_PACKET

        override fun serialize(value: DebugInfoPacket, stream: Sink) {
            ActorUniqueID.serialize(value.playerUniqueID, stream)
            ProtoHelper.serializeList(value.data, stream, Proto.Byte)
        }

        override fun deserialize(stream: Source): DebugInfoPacket {
            return DebugInfoPacket(
                playerUniqueID = ActorUniqueID.deserialize(stream),
                data = ProtoHelper.deserializeList(stream, Proto.Byte)
            )
        }
    }
}
