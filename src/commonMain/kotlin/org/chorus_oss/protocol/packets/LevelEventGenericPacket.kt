package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.Int

data class LevelEventGenericPacket(
    val eventID: Int,
    val serializedEventData: ByteString,
) : Packet(id) {
    companion object : PacketCodec<LevelEventGenericPacket> {
        override val id: Int = 124

        override fun serialize(
            value: LevelEventGenericPacket,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.eventID, stream)
            Proto.ByteString.serialize(value.serializedEventData, stream)
        }

        override fun deserialize(stream: Source): LevelEventGenericPacket {
            return LevelEventGenericPacket(
                eventID = ProtoVAR.Int.deserialize(stream),
                serializedEventData = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
