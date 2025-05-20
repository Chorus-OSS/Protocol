package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int


data class SetLastHurtByPacket(
    val entityType: Int
) : Packet(id) {
    companion object : PacketCodec<SetLastHurtByPacket> {
        override val id: Int
            get() = ProtocolInfo.SET_LAST_HURT_BY_PACKET

        override fun serialize(value: SetLastHurtByPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.entityType, stream)
        }

        override fun deserialize(stream: Source): SetLastHurtByPacket {
            return SetLastHurtByPacket(
                entityType = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
