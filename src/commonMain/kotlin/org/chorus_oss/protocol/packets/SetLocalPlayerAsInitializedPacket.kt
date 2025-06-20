package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorRuntimeID


data class SetLocalPlayerAsInitializedPacket(
    val entityRuntimeID: ULong,
) : Packet(id) {
    companion object : PacketCodec<SetLocalPlayerAsInitializedPacket> {
        override val id: Int = 113

        override fun serialize(
            value: SetLocalPlayerAsInitializedPacket,
            stream: Sink
        ) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
        }

        override fun deserialize(stream: Source): SetLocalPlayerAsInitializedPacket {
            return SetLocalPlayerAsInitializedPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
            )
        }
    }
}
