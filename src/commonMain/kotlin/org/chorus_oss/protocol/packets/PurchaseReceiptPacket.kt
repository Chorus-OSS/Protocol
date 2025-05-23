package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.String

data class PurchaseReceiptPacket(
    val receipts: List<String>
) : Packet(id) {
    companion object : PacketCodec<PurchaseReceiptPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.PURCHASE_RECEIPT_PACKET

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
