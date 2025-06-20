package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.BlockPos

data class BlockPickRequestPacket(
    val position: BlockPos,
    var withData: Boolean,
    var maxSlots: Byte,
) : Packet(id) {
    companion object : PacketCodec<BlockPickRequestPacket> {
        override val id: Int = 34

        override fun deserialize(stream: Source): BlockPickRequestPacket {
            return BlockPickRequestPacket(
                position = BlockPos.deserialize(stream),
                withData = Proto.Boolean.deserialize(stream),
                maxSlots = Proto.Byte.deserialize(stream)
            )
        }

        override fun serialize(value: BlockPickRequestPacket, stream: Sink) {
            BlockPos.serialize(value.position, stream)
            Proto.Boolean.serialize(value.withData, stream)
            Proto.Byte.serialize(value.maxSlots, stream)
        }
    }
}
