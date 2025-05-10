package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.shared.types.IVector3
import org.chorus_oss.protocol.shared.types.UIVector3

data class AnvilDamagePacket(
    val damageAmount: Byte,
    val blockPosition: IVector3,
) {
    companion object : PacketCodec<AnvilDamagePacket> {
        override val id: Int
            get() = ProtocolInfo.ANVIL_DAMAGE_PACKET

        override fun deserialize(stream: Buffer): AnvilDamagePacket {
            return AnvilDamagePacket(
                damageAmount = Proto.Byte.deserialize(stream),
                blockPosition = UIVector3.deserialize(stream)
            )
        }

        override fun serialize(value: AnvilDamagePacket, stream: Buffer) {
            Proto.Byte.serialize(value.damageAmount, stream)
            UIVector3.serialize(value.blockPosition, stream)
        }
    }
}
