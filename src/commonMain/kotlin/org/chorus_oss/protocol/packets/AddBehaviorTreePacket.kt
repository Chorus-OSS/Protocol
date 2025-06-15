package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String

data class AddBehaviorTreePacket(
    val behaviorTreeJSON: String
) : Packet(id) {
    companion object : PacketCodec<AddBehaviorTreePacket> {
        override val id: Int = 89

        override fun deserialize(stream: Source): AddBehaviorTreePacket {
            return AddBehaviorTreePacket(
                behaviorTreeJSON = Proto.String.deserialize(stream)
            )
        }

        override fun serialize(value: AddBehaviorTreePacket, stream: Sink) {
            Proto.String.serialize(value.behaviorTreeJSON, stream)
        }
    }
}
