package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f


data class MoveActorAbsolutePacket(
    val entityRuntimeID: ActorRuntimeID,
    val flags: Byte,
    val position: Vector3f,
    val rotation: Vector3f,
) : Packet(id) {
    companion object : PacketCodec<MoveActorAbsolutePacket> {
        init { PacketRegistry.register(this) }

        const val FLAG_ON_GROUND: Byte = 0x01
        const val FLAG_TELEPORT: Byte = 0x02
        const val FLAG_FORCE_MOVE: Byte = 0x04

        override val id: Int
            get() = ProtocolInfo.MOVE_ACTOR_ABSOLUTE_PACKET

        override fun serialize(
            value: MoveActorAbsolutePacket,
            stream: Sink
        ) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            Proto.Byte.serialize(value.flags, stream)
            Vector3f.serialize(value.position, stream)
            Proto.Byte.serialize((value.rotation.x / (360.0f / 256.0f)).toInt().toByte(), stream)
            Proto.Byte.serialize((value.rotation.y / (360.0f / 256.0f)).toInt().toByte(), stream)
            Proto.Byte.serialize((value.rotation.z / (360.0f / 256.0f)).toInt().toByte(), stream)
        }

        override fun deserialize(stream: Source): MoveActorAbsolutePacket {
            return MoveActorAbsolutePacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                flags = Proto.Byte.deserialize(stream),
                position = Vector3f.deserialize(stream),
                rotation = Vector3f(
                    x = Proto.Byte.deserialize(stream).toFloat() * (360.0f / 256.0f),
                    y = Proto.Byte.deserialize(stream).toFloat() * (360.0f / 256.0f),
                    z = Proto.Byte.deserialize(stream).toFloat() * (360.0f / 256.0f),
                )
            )
        }
    }
}
