package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.BlockPos


data class LabTablePacket(
    val actionType: ActionType,
    val blockPosition: BlockPos,
    val reactionType: Byte
) : Packet(id) {
    companion object : PacketCodec<LabTablePacket> {
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

        override val id: Int = 109

        override fun serialize(value: LabTablePacket, stream: Sink) {
            ActionType.serialize(value.actionType, stream)
            BlockPos.serialize(value.blockPosition, stream)
            Proto.Byte.serialize(value.reactionType, stream)
        }

        override fun deserialize(stream: Source): LabTablePacket {
            return LabTablePacket(
                actionType = ActionType.deserialize(stream),
                blockPosition = BlockPos.deserialize(stream),
                reactionType = Proto.Byte.deserialize(stream)
            )
        }
    }
}
