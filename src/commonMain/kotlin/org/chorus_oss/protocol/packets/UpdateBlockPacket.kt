package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos


data class UpdateBlockPacket(
    val position: BlockPos,
    val newBlockRuntimeID: UInt,
    val flags: UInt,
    val layer: UInt,
) : Packet(id) {
    companion object : PacketCodec<UpdateBlockPacket> {


        const val FLAG_NEIGHBORS: UInt = 0x1u
        const val FLAG_NETWORK: UInt = 0x2u
        const val FLAG_NO_GRAPHICS: UInt = 0x4u
        const val FLAG_PRIORITY: UInt = 0x8u

        override val id: Int = 21

        override fun serialize(value: UpdateBlockPacket, stream: Sink) {
            NetBlockPos.serialize(value.position, stream)
            ProtoVAR.UInt.serialize(value.newBlockRuntimeID, stream)
            ProtoVAR.UInt.serialize(value.flags, stream)
            ProtoVAR.UInt.serialize(value.layer, stream)
        }

        override fun deserialize(stream: Source): UpdateBlockPacket {
            return UpdateBlockPacket(
                position = NetBlockPos.deserialize(stream),
                newBlockRuntimeID = ProtoVAR.UInt.deserialize(stream),
                flags = ProtoVAR.UInt.deserialize(stream),
                layer = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
