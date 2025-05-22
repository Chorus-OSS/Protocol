package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int

data class LevelEventGenericPacket(
    val eventID: Int,
    val serializedEventData: List<Byte>
) : Packet(id) {
    companion object : PacketCodec<LevelEventGenericPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.LEVEL_EVENT_GENERIC_PACKET

        override fun serialize(
            value: LevelEventGenericPacket,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.eventID, stream)
            ProtoHelper.serializeList(value.serializedEventData, stream, Proto.Byte)
        }

        override fun deserialize(stream: Source): LevelEventGenericPacket {
            return LevelEventGenericPacket(
                eventID = ProtoVAR.Int.deserialize(stream),
                serializedEventData = ProtoHelper.deserializeList(stream, Proto.Byte)
            )
        }
    }
}
