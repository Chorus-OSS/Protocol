package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.types.ActorRuntimeID


data class SetLocalPlayerAsInitializedPacket(
    val entityRuntimeID: ActorRuntimeID,
) : Packet(id) {
    companion object : PacketCodec<SetLocalPlayerAsInitializedPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.SET_LOCAL_PLAYER_AS_INITIALIZED_PACKET

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
