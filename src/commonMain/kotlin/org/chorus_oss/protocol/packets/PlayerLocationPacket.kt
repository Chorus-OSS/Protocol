package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f

data class PlayerLocationPacket(
    val type: Type,
    val actorRuntimeID: ActorRuntimeID,
    val position: Vector3f?,
) : Packet(id) {
    companion object : PacketCodec<PlayerLocationPacket> {
        init { PacketRegistry.register(this) }

        enum class Type {
            Coordinates,
            Hide;

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    ProtoLE.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[ProtoLE.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.PLAYER_LOCATION_PACKET

        override fun serialize(
            value: PlayerLocationPacket,
            stream: Sink
        ) {
            Type.serialize(value.type, stream)
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            when (value.type) {
                Type.Coordinates -> Vector3f.serialize(value.position as Vector3f, stream)
                else -> Unit
            }
        }

        override fun deserialize(stream: Source): PlayerLocationPacket {
            val type: Type
            return PlayerLocationPacket(
                type = Type.deserialize(stream).also { type = it },
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                position = when (type) {
                    Type.Coordinates -> Vector3f.deserialize(stream)
                    else -> null
                }
            )
        }
    }
}