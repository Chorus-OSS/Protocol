package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String

data class PlayerVideoCapturePacket(
    val action: Action,
    val frameRate: Int?,
    val filePrefix: String?,
) : Packet(id) {
    companion object : PacketCodec<PlayerVideoCapturePacket> {
        enum class Action {
            Stop,
            Start;

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
            get() = ProtocolInfo.PLAYER_VIDEO_CAPTURE_PACKET

        override fun serialize(
            value: PlayerVideoCapturePacket,
            stream: Sink
        ) {
            Action.serialize(value.action, stream)
            when (value.action) {
                Action.Start -> ProtoLE.Int.serialize(value.frameRate as Int, stream)
                else -> Unit
            }
            when (value.action) {
                Action.Start -> Proto.String.serialize(value.filePrefix as String, stream)
                else -> Unit
            }
        }

        override fun deserialize(stream: Source): PlayerVideoCapturePacket {
            val action: Action
            return PlayerVideoCapturePacket(
                action = Action.deserialize(stream).also { action = it},
                frameRate = when (action) {
                    Action.Start -> ProtoLE.Int.deserialize(stream)
                    else -> null
                },
                filePrefix = when (action) {
                    Action.Start -> Proto.String.deserialize(stream)
                    else -> null
                },
            )
        }
    }
}
