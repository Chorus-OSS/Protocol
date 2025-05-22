package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.ActorRuntimeID

data class PlayerUpdateEntityOverridesPacket(
    val entityRuntimeID: ActorRuntimeID,
    val propertyIndex: UInt,
    val type: Type,
    val intValue: Int?,
    val floatValue: Float?,
) : Packet(id) {
    companion object : PacketCodec<PlayerUpdateEntityOverridesPacket> {
        init { PacketRegistry.register(this) }

        enum class Type {
            ClearAll,
            Remove,
            Int,
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
            get() = ProtocolInfo.PLAYER_UPDATE_ENTITY_OVERRIDES_PACKET

        override fun serialize(
            value: PlayerUpdateEntityOverridesPacket,
            stream: Sink
        ) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            ProtoVAR.UInt.serialize(value.propertyIndex, stream)
            Type.serialize(value.type, stream)
            when (value.type) {
                Type.Int -> ProtoLE.Int.serialize(value.intValue as Int, stream)
                else -> Unit
            }
            when (value.type) {
                Type.Float -> ProtoLE.Float.serialize(value.floatValue as Float, stream)
                else -> Unit
            }
        }

        override fun deserialize(stream: Source): PlayerUpdateEntityOverridesPacket {
            val type: Type
            return PlayerUpdateEntityOverridesPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                propertyIndex = ProtoVAR.UInt.deserialize(stream),
                type = Type.deserialize(stream).also { type = it },
                intValue = when (type) {
                    Type.Int -> ProtoLE.Int.deserialize(stream)
                    else -> null
                },
                floatValue = when (type) {
                    Type.Float -> ProtoLE.Float.deserialize(stream)
                    else -> null
                },
            )
        }
    }
}
