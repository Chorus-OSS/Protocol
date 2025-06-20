package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorUniqueID


data class MapCreateLockedCopyPacket(
    val originalMapID: Long,
    val newMapID: Long,
) : Packet(id) {
    companion object : PacketCodec<MapCreateLockedCopyPacket> {
        override val id: Int = 131

        override fun serialize(
            value: MapCreateLockedCopyPacket,
            stream: Sink
        ) {
            ActorUniqueID.serialize(value.originalMapID, stream)
            ActorUniqueID.serialize(value.newMapID, stream)
        }

        override fun deserialize(stream: Source): MapCreateLockedCopyPacket {
            return MapCreateLockedCopyPacket(
                originalMapID = ActorUniqueID.deserialize(stream),
                newMapID = ActorUniqueID.deserialize(stream),
            )
        }

    }
}
