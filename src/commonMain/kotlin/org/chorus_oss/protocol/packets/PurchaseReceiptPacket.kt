package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.String

data class PurchaseReceiptPacket(
    val receipts: List<String>
) : Packet(id) {
    companion object : PacketCodec<PurchaseReceiptPacket> {
        override val id: Int = 92

        override fun serialize(value: PurchaseReceiptPacket, stream: Sink) {
            ProtoHelper.serializeList(value.receipts, stream, Proto.String)
        }

        override fun deserialize(stream: Source): PurchaseReceiptPacket {
            return PurchaseReceiptPacket(
                receipts = ProtoHelper.deserializeList(stream, Proto.String)
            )
        }
    }
}
