package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String

data class AddBehaviorTreePacket(
    val behaviorTreeJSON: String
) {
    companion object : PacketCodec<AddBehaviorTreePacket> {
        override val id: Int
            get() = ProtocolInfo.ADD_BEHAVIOR_TREE_PACKET

        override fun deserialize(stream: Buffer): AddBehaviorTreePacket {
            return AddBehaviorTreePacket(
                behaviorTreeJSON = Proto.String.deserialize(stream)
            )
        }

        override fun serialize(value: AddBehaviorTreePacket, stream: Buffer) {
            Proto.String.serialize(value.behaviorTreeJSON, stream)
        }
    }
}
