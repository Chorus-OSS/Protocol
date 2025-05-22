package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.IVector3


data class LabTablePacket(
    val actionType: ActionType,
    val blockPosition: IVector3,
    val reactionType: Byte
) : Packet(id) {
    companion object : PacketCodec<LabTablePacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class ActionType {
            Combine,
            React,
            Reset;

            companion object : ProtoCodec<ActionType> {
                override fun serialize(
                    value: ActionType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): ActionType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.LAB_TABLE_PACKET

        override fun serialize(value: LabTablePacket, stream: Sink) {
            ActionType.serialize(value.actionType, stream)
            IVector3.serialize(value.blockPosition, stream)
            Proto.Byte.serialize(value.reactionType, stream)
        }

        override fun deserialize(stream: Source): LabTablePacket {
            return LabTablePacket(
                actionType = ActionType.deserialize(stream),
                blockPosition = IVector3.deserialize(stream),
                reactionType = Proto.Byte.deserialize(stream)
            )
        }
    }
}
