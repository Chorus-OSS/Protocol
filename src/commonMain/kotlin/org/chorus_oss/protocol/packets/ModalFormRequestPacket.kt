package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt


data class ModalFormRequestPacket(
    val formID: UInt,
    val formData: String,
) : Packet(id) {
    companion object : PacketCodec<ModalFormRequestPacket> {
        override val id: Int = 100

        override fun serialize(value: ModalFormRequestPacket, stream: Sink) {
            ProtoVAR.UInt.serialize(value.formID, stream)
            Proto.String.serialize(value.formData, stream)
        }

        override fun deserialize(stream: Source): ModalFormRequestPacket {
            return ModalFormRequestPacket(
                formID = ProtoVAR.UInt.deserialize(stream),
                formData = Proto.String.deserialize(stream),
            )
        }
    }
}
