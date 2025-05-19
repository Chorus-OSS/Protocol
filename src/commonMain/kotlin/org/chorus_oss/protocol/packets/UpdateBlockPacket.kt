package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3


data class UpdateBlockPacket(
    val position: IVector3,
    val newBlockRuntimeID: UInt,
    val flags: UInt,
    val layer: UInt,
) : Packet(id) {
    companion object : PacketCodec<UpdateBlockPacket> {
        const val FLAG_NEIGHBORS: UInt = 0x1u
        const val FLAG_NETWORK: UInt = 0x2u
        const val FLAG_NO_GRAPHICS: UInt = 0x4u
        const val FLAG_PRIORITY: UInt = 0x8u

        override val id: Int
            get() = ProtocolInfo.UPDATE_BLOCK_PACKET

        override fun serialize(value: UpdateBlockPacket, stream: Sink) {
            UIVector3.serialize(value.position, stream)
            ProtoVAR.UInt.serialize(value.newBlockRuntimeID, stream)
            ProtoVAR.UInt.serialize(value.flags, stream)
            ProtoVAR.UInt.serialize(value.layer, stream)
        }

        override fun deserialize(stream: Source): UpdateBlockPacket {
            return UpdateBlockPacket(
                position = UIVector3.deserialize(stream),
                newBlockRuntimeID = ProtoVAR.UInt.deserialize(stream),
                flags = ProtoVAR.UInt.deserialize(stream),
                layer = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
