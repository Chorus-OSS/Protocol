package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AgentAnimationPacket(
    val animation: Byte,
    val runtimeID: ActorRuntimeID,
) : Packet(id) {
    companion object : PacketCodec<AgentAnimationPacket> {
        override val id: Int = 304

        override fun deserialize(stream: Source): AgentAnimationPacket {
            return AgentAnimationPacket(
                animation = Proto.Byte.deserialize(stream),
                runtimeID = ActorRuntimeID.deserialize(stream),
            )
        }

        override fun serialize(value: AgentAnimationPacket, stream: Sink) {
            Proto.Byte.serialize(value.animation, stream)
            ActorRuntimeID.serialize(value.runtimeID, stream)
        }
    }
}
