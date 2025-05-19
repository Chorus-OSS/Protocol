package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.AbilitiesData


data class UpdateAbilitiesPacket(
    val abilitiesData: AbilitiesData,
) : Packet(id) {
    companion object : PacketCodec<UpdateAbilitiesPacket> {
        override val id: Int
            get() = ProtocolInfo.UPDATE_ABILITIES_PACKET

        override fun serialize(value: UpdateAbilitiesPacket, stream: Sink) {
            AbilitiesData.serialize(value.abilitiesData, stream)
        }

        override fun deserialize(stream: Source): UpdateAbilitiesPacket {
            return UpdateAbilitiesPacket(
                abilitiesData = AbilitiesData.deserialize(stream)
            )
        }
    }
}
