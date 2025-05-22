package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f


data class MoveActorDeltaPacket(
    val flags: UShort,
    val entityRuntimeID: ActorRuntimeID,
    val position: Vector3f,
    val rotation: Vector3f,
) : Packet(id) {
    companion object : PacketCodec<MoveActorDeltaPacket> {
        init { PacketRegistry.register(this) }

        const val FLAG_HAS_X: UShort = 0x1u
        const val FLAG_HAS_Y: UShort = 0x2u
        const val FLAG_HAS_Z: UShort = 0x4u
        const val FLAG_HAS_ROT_X: UShort = 0x8u
        const val FLAG_HAS_ROT_Y: UShort = 0x10u
        const val FLAG_HAS_ROT_Z: UShort = 0x20u
        const val FLAG_ON_GROUND: UShort = 0x40u
        const val FLAG_TELEPORT: UShort = 0x80u
        const val FLAG_FORCE_MOVE: UShort = 0x100u

        override val id: Int
            get() = ProtocolInfo.MOVE_ENTITY_DELTA_PACKET

        override fun serialize(value: MoveActorDeltaPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            ProtoLE.UShort.serialize(value.flags, stream)
            when (value.flags and FLAG_HAS_X != 0u.toUShort()) {
                true -> ProtoLE.Float.serialize(value.position.x, stream)
                false -> Unit
            }

            when (value.flags and FLAG_HAS_Y != 0u.toUShort()) {
                true -> ProtoLE.Float.serialize(value.position.y, stream)
                false -> Unit
            }

            when (value.flags and FLAG_HAS_Z != 0u.toUShort()) {
                true -> ProtoLE.Float.serialize(value.position.z, stream)
                false -> Unit
            }

            when (value.flags and FLAG_HAS_ROT_X != 0u.toUShort()) {
                true -> ProtoLE.Float.serialize(value.rotation.x, stream)
                false -> Unit
            }

            when (value.flags and FLAG_HAS_ROT_Y != 0u.toUShort()) {
                true -> ProtoLE.Float.serialize(value.rotation.y, stream)
                false -> Unit
            }

            when (value.flags and FLAG_HAS_ROT_Z != 0u.toUShort()) {
                true -> ProtoLE.Float.serialize(value.rotation.z, stream)
                false -> Unit
            }
        }

        override fun deserialize(stream: Source): MoveActorDeltaPacket {
            val flags: UShort
            return MoveActorDeltaPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                flags = ProtoLE.UShort.deserialize(stream).also { flags = it },
                position = Vector3f(
                    x = when (flags and FLAG_HAS_X != 0u.toUShort()) {
                        true -> ProtoLE.Float.deserialize(stream)
                        false -> 0f
                    },
                    y = when (flags and FLAG_HAS_Y != 0u.toUShort()) {
                        true -> ProtoLE.Float.deserialize(stream)
                        false -> 0f
                    },
                    z = when (flags and FLAG_HAS_Z != 0u.toUShort()) {
                        true -> ProtoLE.Float.deserialize(stream)
                        false -> 0f
                    }
                ),
                rotation = Vector3f(
                    x = when (flags and FLAG_HAS_ROT_X != 0u.toUShort()) {
                        true -> ProtoLE.Float.deserialize(stream)
                        false -> 0f
                    },
                    y = when (flags and FLAG_HAS_ROT_Y != 0u.toUShort()) {
                        true -> ProtoLE.Float.deserialize(stream)
                        false -> 0f
                    },
                    z = when (flags and FLAG_HAS_ROT_Z != 0u.toUShort()) {
                        true -> ProtoLE.Float.deserialize(stream)
                        false -> 0f
                    }
                )
            )
        }
    }
}
