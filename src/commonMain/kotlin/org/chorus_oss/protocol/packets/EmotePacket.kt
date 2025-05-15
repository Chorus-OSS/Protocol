package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.ActorRuntimeID


class EmotePacket(
    val actorRuntimeID: ActorRuntimeID,
    val emoteID: String,
    val emoteLength: UInt,
    val xuid: String,
    val platformID: String,
    val flags: Byte
) : Packet(id) {
    companion object : PacketCodec<EmotePacket> {
        const val FLAG_SERVER_SIDE: Byte = 0x1
        const val FLAG_MUTE_CHAT: Byte = 0x2

        override val id: Int
            get() = ProtocolInfo.EMOTE_PACKET

        override fun serialize(value: EmotePacket, stream: Sink) {
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            Proto.String.serialize(value.emoteID, stream)
            ProtoVAR.UInt.serialize(value.emoteLength, stream)
            Proto.String.serialize(value.xuid, stream)
            Proto.String.serialize(value.platformID, stream)
            Proto.Byte.serialize(value.flags, stream)
        }

        override fun deserialize(stream: Source): EmotePacket {
            return EmotePacket(
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                emoteID = Proto.String.deserialize(stream),
                emoteLength = ProtoVAR.UInt.deserialize(stream),
                xuid = Proto.String.deserialize(stream),
                platformID = Proto.String.deserialize(stream),
                flags = Proto.Byte.deserialize(stream),
            )
        }
    }
}
