package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AgentAnimationPacket(
    val animation: Byte,
    val runtimeID: ActorRuntimeID,
) {
    companion object : PacketCodec<AgentAnimationPacket> {
        override val id: Int
            get() = ProtocolInfo.AGENT_ANIMATION

        override fun deserialize(stream: Buffer): AgentAnimationPacket {
            return AgentAnimationPacket(
                animation = Proto.Byte.deserialize(stream),
                runtimeID = ProtoVAR.Long.deserialize(stream),
            )
        }

        override fun serialize(value: AgentAnimationPacket, stream: Buffer) {
            Proto.Byte.serialize(value.animation, stream)
            ProtoVAR.Long.serialize(value.runtimeID, stream)
        }
    }
}
