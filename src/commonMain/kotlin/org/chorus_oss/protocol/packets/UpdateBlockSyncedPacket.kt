package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3


data class UpdateBlockSyncedPacket(
    val position: IVector3,
    val newBlockRuntimeID: UInt,
    val flags: UInt,
    val layer: UInt,
    val actorUniqueID: Long,
    val transitionType: TransitionType,
) : Packet(id) {
    companion object : PacketCodec<UpdateBlockSyncedPacket> {


        const val FLAG_NEIGHBORS: UInt = 0x1u
        const val FLAG_NETWORK: UInt = 0x2u
        const val FLAG_NO_GRAPHICS: UInt = 0x4u
        const val FLAG_PRIORITY: UInt = 0x8u

        enum class TransitionType {
            BlockToEntity,
            EntityToBlock;

            companion object : ProtoCodec<TransitionType> {
                override fun serialize(
                    value: TransitionType,
                    stream: Sink
                ) {
                    ProtoVAR.ULong.serialize(value.ordinal.toULong(), stream)
                }

                override fun deserialize(stream: Source): TransitionType {
                    return entries[ProtoVAR.ULong.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 110

        override fun serialize(
            value: UpdateBlockSyncedPacket,
            stream: Sink
        ) {
            UIVector3.serialize(value.position, stream)
            ProtoVAR.UInt.serialize(value.newBlockRuntimeID, stream)
            ProtoVAR.UInt.serialize(value.flags, stream)
            ProtoVAR.UInt.serialize(value.layer, stream)
            ActorUniqueID.serialize(value.actorUniqueID, stream)
            TransitionType.serialize(value.transitionType, stream)
        }

        override fun deserialize(stream: Source): UpdateBlockSyncedPacket {
            return UpdateBlockSyncedPacket(
                position = UIVector3.deserialize(stream),
                newBlockRuntimeID = ProtoVAR.UInt.deserialize(stream),
                flags = ProtoVAR.UInt.deserialize(stream),
                layer = ProtoVAR.UInt.deserialize(stream),
                actorUniqueID = ActorUniqueID.deserialize(stream),
                transitionType = TransitionType.deserialize(stream),
            )
        }
    }
}
