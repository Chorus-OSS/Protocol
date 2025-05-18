package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.types.PlayerAbility


data class RequestAbilityPacket(
    val ability: PlayerAbility,
    val type: Type,
    val boolValue: Boolean?,
    val floatValue: Float?,
) : Packet(id) {
    companion object : PacketCodec<RequestAbilityPacket> {
        enum class Type {
            None,
            Boolean,
            Float;

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.REQUEST_ABILITY_PACKET

        override fun serialize(value: RequestAbilityPacket, stream: Sink) {
            PlayerAbility.serialize(value.ability, stream)
            Type.serialize(value.type, stream)
            when (value.type) {
                Type.Boolean -> {
                    Proto.Boolean.serialize(value.boolValue as Boolean, stream)
                    ProtoLE.Float.serialize(0f, stream)
                }
                else -> Unit
            }
            when (value.type) {
                Type.Float -> {
                    Proto.Boolean.serialize(false, stream)
                    ProtoLE.Float.serialize(value.floatValue as Float, stream)
                }
                else -> Unit
            }
        }

        override fun deserialize(stream: Source): RequestAbilityPacket {
            val type: Type
            return RequestAbilityPacket(
                ability = PlayerAbility.deserialize(stream),
                type = Type.deserialize(stream).also { type = it },
                boolValue = when (type) {
                    Type.Boolean -> {
                        val boolValue = Proto.Boolean.deserialize(stream)
                        ProtoLE.Float.deserialize(stream)
                        boolValue
                    }
                    else -> null
                },
                floatValue = when (type) {
                    Type.Float -> {
                        Proto.Boolean.deserialize(stream)
                        val floatValue = ProtoLE.Float.deserialize(stream)
                        floatValue
                    }
                    else -> null
                }
            )
        }
    }
}
