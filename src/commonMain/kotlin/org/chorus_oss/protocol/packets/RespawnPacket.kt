package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f


data class RespawnPacket(
    val position: Vector3f,
    val state: State,
    val entityRuntimeID: ActorRuntimeID,
) : Packet(id) {
    companion object : PacketCodec<RespawnPacket> {
        enum class State {
            SearchingForSpawn,
            ReadyToSpawn,
            ClientReadyToSpawn;

            companion object : ProtoCodec<State> {
                override fun serialize(
                    value: State,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): State {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 45

        override fun serialize(value: RespawnPacket, stream: Sink) {
            Vector3f.serialize(value.position, stream)
            State.serialize(value.state, stream)
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
        }

        override fun deserialize(stream: Source): RespawnPacket {
            return RespawnPacket(
                position = Vector3f.deserialize(stream),
                state = State.deserialize(stream),
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
            )
        }
    }
}
