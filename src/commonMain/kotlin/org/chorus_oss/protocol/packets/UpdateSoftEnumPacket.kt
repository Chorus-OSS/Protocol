package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String


data class UpdateSoftEnumPacket(
    val enumType: String,
    val options: List<String>,
    val actionType: ActionType,
) : Packet(id) {
    companion object : PacketCodec<UpdateSoftEnumPacket> {
        enum class ActionType {
            Add,
            Remove,
            Set;

            companion object : ProtoCodec<ActionType> {
                override fun serialize(
                    value: ActionType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): ActionType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.UPDATE_SOFT_ENUM_PACKET

        override fun serialize(value: UpdateSoftEnumPacket, stream: Sink) {
            Proto.String.serialize(value.enumType, stream)
            ProtoHelper.serializeList(value.options, stream, Proto.String)
            ActionType.serialize(value.actionType, stream)
        }

        override fun deserialize(stream: Source): UpdateSoftEnumPacket {
            return UpdateSoftEnumPacket(
                enumType = Proto.String.deserialize(stream),
                options = ProtoHelper.deserializeList(stream, Proto.String),
                actionType = ActionType.deserialize(stream),
            )
        }
    }
}
