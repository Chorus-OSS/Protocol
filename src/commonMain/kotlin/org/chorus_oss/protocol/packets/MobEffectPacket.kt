package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorRuntimeID


data class MobEffectPacket(
    val entityRuntimeID: ActorRuntimeID,
    val operation: Operation,
    val effectType: Int,
    val amplifier: Int,
    val particles: Boolean,
    val duration: Int,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<MobEffectPacket> {
        enum class Operation(val netOrdinal: Byte) {
            Add(1),
            Modify(2),
            Remove(3);

            companion object : ProtoCodec<Operation> {
                override fun serialize(
                    value: Operation,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.netOrdinal, stream)
                }

                override fun deserialize(stream: Source): Operation {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.netOrdinal == it }!!
                    }
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.MOB_EFFECT_PACKET

        override fun serialize(value: MobEffectPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            Operation.serialize(value.operation, stream)
            ProtoVAR.Int.serialize(value.effectType, stream)
            ProtoVAR.Int.serialize(value.amplifier, stream)
            Proto.Boolean.serialize(value.particles, stream)
            ProtoVAR.Int.serialize(value.duration, stream)
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): MobEffectPacket {
            return MobEffectPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                operation = Operation.deserialize(stream),
                effectType = ProtoVAR.Int.deserialize(stream),
                amplifier = ProtoVAR.Int.deserialize(stream),
                particles = Proto.Boolean.deserialize(stream),
                duration = ProtoVAR.Int.deserialize(stream),
                tick = ProtoVAR.ULong.deserialize(stream),
            )
        }
    }
}
