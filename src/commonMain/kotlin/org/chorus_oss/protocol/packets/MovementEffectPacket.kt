package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorRuntimeID


data class MovementEffectPacket(
    val entityRuntimeID: ActorRuntimeID,
    val type: Type,
    val duration: Int,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<MovementEffectPacket> {
        enum class Type {
            GlideBoost;

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[ProtoVAR.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.MOVEMENT_EFFECT_PACKET

        override fun serialize(value: MovementEffectPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            Type.serialize(value.type, stream)
            ProtoVAR.Int.serialize(value.duration, stream)
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): MovementEffectPacket {
            return MovementEffectPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                type = Type.deserialize(stream),
                duration = ProtoVAR.Int.deserialize(stream),
                tick = ProtoVAR.ULong.deserialize(stream)
            )
        }
    }
}
