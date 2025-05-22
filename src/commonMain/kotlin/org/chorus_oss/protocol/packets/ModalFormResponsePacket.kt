package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt


data class ModalFormResponsePacket(
    val formID: UInt,
    val responseData: String?,
    val cancelReason: CancelReason?,
) : Packet(id) {
    companion object : PacketCodec<ModalFormResponsePacket> {
        init { PacketRegistry.register(this) }

        enum class CancelReason {
            UserClosed,
            UserBusy;

            companion object : ProtoCodec<CancelReason> {
                override fun serialize(
                    value: CancelReason,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): CancelReason {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.MODAL_FORM_RESPONSE_PACKET

        override fun serialize(
            value: ModalFormResponsePacket,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.formID, stream)
            ProtoHelper.serializeNullable(value.responseData, stream, Proto.String)
            ProtoHelper.serializeNullable(value.cancelReason, stream, CancelReason)
        }

        override fun deserialize(stream: Source): ModalFormResponsePacket {
            return ModalFormResponsePacket(
                formID = ProtoVAR.UInt.deserialize(stream),
                responseData = ProtoHelper.deserializeNullable(stream, Proto.String),
                cancelReason = ProtoHelper.deserializeNullable(stream, CancelReason)
            )
        }
    }
}
