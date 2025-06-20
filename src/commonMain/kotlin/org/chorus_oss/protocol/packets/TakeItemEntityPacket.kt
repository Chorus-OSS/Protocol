package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorRuntimeID


data class TakeItemEntityPacket(
    val itemEntityRuntimeID: ULong,
    val takerEntityRuntimeID: ULong,
) : Packet(id) {
    companion object : PacketCodec<TakeItemEntityPacket> {
        override val id: Int = 17

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
