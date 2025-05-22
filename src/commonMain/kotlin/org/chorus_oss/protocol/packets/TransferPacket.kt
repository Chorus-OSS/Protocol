package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UShort


data class TransferPacket(
    val address: String,
    val port: UShort,
    val reloadWorld: Boolean,
) : Packet(id) {
    companion object : PacketCodec<TransferPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.TRANSFER_PACKET

        override fun serialize(value: TransferPacket, stream: Sink) {
            Proto.String.serialize(value.address, stream)
            ProtoLE.UShort.serialize(value.port, stream)
            Proto.Boolean.serialize(value.reloadWorld, stream)
        }

        override fun deserialize(stream: Source): TransferPacket {
            return TransferPacket(
                address = Proto.String.deserialize(stream),
                port = ProtoLE.UShort.deserialize(stream),
                reloadWorld = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
