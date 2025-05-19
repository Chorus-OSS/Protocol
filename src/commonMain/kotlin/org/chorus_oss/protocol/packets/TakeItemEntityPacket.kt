package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorRuntimeID


data class TakeItemEntityPacket(
    val itemEntityRuntimeID: ActorRuntimeID,
    val takerEntityRuntimeID: ActorRuntimeID,
) : Packet(id) {
    companion object : PacketCodec<TakeItemEntityPacket> {
        override val id: Int
            get() = ProtocolInfo.TAKE_ITEM_ENTITY_PACKET

        override fun serialize(value: TakeItemEntityPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.itemEntityRuntimeID, stream)
            ActorRuntimeID.serialize(value.takerEntityRuntimeID, stream)
        }

        override fun deserialize(stream: Source): TakeItemEntityPacket {
            return TakeItemEntityPacket(
                itemEntityRuntimeID = ActorRuntimeID.deserialize(stream),
                takerEntityRuntimeID = ActorRuntimeID.deserialize(stream),
            )
        }
    }
}
