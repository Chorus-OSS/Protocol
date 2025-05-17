package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f


data class MovePlayerPacket(
    val entityRuntimeID: ActorRuntimeID,
    val position: Vector3f,
    val pitch: Float,
    val yaw: Float,
    val headYaw: Float,
    val mode: Mode,
    val onGround: Boolean,
    val riddenEntityRuntimeID: ActorRuntimeID,
    val teleportCause: TeleportCause,
    val teleportSourceEntityType: Int,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<MovePlayerPacket> {
        enum class Mode {
            Normal,
            Reset,
            Teleport,
            Rotation;

            companion object : ProtoCodec<Mode> {
                override fun serialize(
                    value: Mode,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Mode {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        enum class TeleportCause {
            Unknown,
            Projectile,
            ChorusFruit,
            Command,
            Behaviour;

            companion object : ProtoCodec<TeleportCause> {
                override fun serialize(
                    value: TeleportCause,
                    stream: Sink
                ) {
                    ProtoLE.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): TeleportCause {
                    return entries[ProtoLE.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.MOVE_PLAYER_PACKET

        override fun serialize(value: MovePlayerPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            Vector3f.serialize(value.position, stream)
            ProtoLE.Float.serialize(value.pitch, stream)
            ProtoLE.Float.serialize(value.yaw, stream)
            ProtoLE.Float.serialize(value.headYaw, stream)
            Mode.serialize(value.mode, stream)
            Proto.Boolean.serialize(value.onGround, stream)
            ActorRuntimeID.serialize(value.riddenEntityRuntimeID, stream)
            when (value.mode) {
                Mode.Teleport -> TeleportCause.serialize(value.teleportCause, stream)
                else -> Unit
            }
            when (value.mode) {
                Mode.Teleport -> ProtoLE.Int.serialize(value.teleportSourceEntityType, stream)
                else -> Unit
            }
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): MovePlayerPacket {
            val mode: Mode
            return MovePlayerPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                position = Vector3f.deserialize(stream),
                pitch = ProtoLE.Float.deserialize(stream),
                yaw = ProtoLE.Float.deserialize(stream),
                headYaw = ProtoLE.Float.deserialize(stream),
                mode = Mode.deserialize(stream).also { mode = it },
                onGround = Proto.Boolean.deserialize(stream),
                riddenEntityRuntimeID = ActorRuntimeID.deserialize(stream),
                teleportCause = when (mode) {
                    Mode.Teleport -> TeleportCause.deserialize(stream)
                    else -> TeleportCause.Unknown
                },
                teleportSourceEntityType = when (mode) {
                    Mode.Teleport -> ProtoLE.Int.deserialize(stream)
                    else -> 0
                },
                tick = ProtoVAR.ULong.deserialize(stream)
            )
        }
    }
}
