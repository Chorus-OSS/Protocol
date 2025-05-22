package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3

data class AnvilDamagePacket(
    val damageAmount: Byte,
    val blockPosition: IVector3,
) : Packet(id) {
    companion object : PacketCodec<AnvilDamagePacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.ANVIL_DAMAGE_PACKET

        override fun deserialize(stream: Source): AnvilDamagePacket {
            return AnvilDamagePacket(
                damageAmount = Proto.Byte.deserialize(stream),
                blockPosition = UIVector3.deserialize(stream)
            )
        }

        override fun serialize(value: AnvilDamagePacket, stream: Sink) {
            Proto.Byte.serialize(value.damageAmount, stream)
            UIVector3.serialize(value.blockPosition, stream)
        }
    }
}
