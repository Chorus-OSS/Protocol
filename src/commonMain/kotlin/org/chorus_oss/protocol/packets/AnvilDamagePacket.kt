package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos

data class AnvilDamagePacket(
    val damageAmount: Byte,
    val blockPosition: BlockPos,
) : Packet(id) {
    companion object : PacketCodec<AnvilDamagePacket> {
        override val id: Int = 141

        override fun deserialize(stream: Source): AnvilDamagePacket {
            return AnvilDamagePacket(
                damageAmount = Proto.Byte.deserialize(stream),
                blockPosition = NetBlockPos.deserialize(stream)
            )
        }

        override fun serialize(value: AnvilDamagePacket, stream: Sink) {
            Proto.Byte.serialize(value.damageAmount, stream)
            NetBlockPos.serialize(value.blockPosition, stream)
        }
    }
}
