package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String


data class LessonProgressPacket(
    val action: Action,
    val score: Int,
    val identifier: String,
) : Packet(id) {
    companion object : PacketCodec<LessonProgressPacket> {
        init { PacketRegistry.register(this) }

        enum class Action {
            Start,
            Complete,
            Restart;

            companion object : ProtoCodec<Action> {
                override fun serialize(
                    value: Action,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Action {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.LESSON_PROGRESS_PACKET

        override fun serialize(value: LessonProgressPacket, stream: Sink) {
            Action.serialize(value.action, stream)
            ProtoVAR.Int.serialize(value.score, stream)
            Proto.String.serialize(value.identifier, stream)
        }

        override fun deserialize(stream: Source): LessonProgressPacket {
            return LessonProgressPacket(
                action = Action.deserialize(stream),
                score = ProtoVAR.Int.deserialize(stream),
                identifier = Proto.String.deserialize(stream)
            )
        }
    }
}
