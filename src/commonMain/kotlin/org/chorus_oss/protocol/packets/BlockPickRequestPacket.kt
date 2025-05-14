package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.shared.types.IVector3

data class BlockPickRequestPacket(
    val position: IVector3,
    var withData: Boolean,
    var maxSlots: Byte,
) : Packet(id) {
    companion object : PacketCodec<BlockPickRequestPacket> {
        override val id: Int
            get() = ProtocolInfo.BLOCK_PICK_REQUEST_PACKET

        override fun deserialize(stream: Source): BlockPickRequestPacket {
            return BlockPickRequestPacket(
                position = IVector3.deserialize(stream),
                withData = Proto.Boolean.deserialize(stream),
                maxSlots = Proto.Byte.deserialize(stream)
            )
        }

        override fun serialize(value: BlockPickRequestPacket, stream: Sink) {
            IVector3.serialize(value.position, stream)
            Proto.Boolean.serialize(value.withData, stream)
            Proto.Byte.serialize(value.maxSlots, stream)
        }
    }
}
