package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.AgentActionType

data class AgentActionEventPacket(
    val requestId: String,
    val action: AgentActionType,
    val response: String,
) : Packet(id) {
    companion object : PacketCodec<AgentActionEventPacket> {
        override val id: Int
            get() = ProtocolInfo.AGENT_ACTION_EVENT_PACKET

        override fun deserialize(stream: Source): AgentActionEventPacket {
            return AgentActionEventPacket(
                requestId = Proto.String.deserialize(stream),
                action = AgentActionType.entries[ProtoLE.Int.deserialize(stream)],
                response = Proto.String.deserialize(stream),
            )
        }

        override fun serialize(value: AgentActionEventPacket, stream: Sink) {
            Proto.String.serialize(value.requestId, stream)
            ProtoLE.Int.serialize(value.action.ordinal, stream)
            Proto.String.serialize(value.response, stream)
        }
    }
}
