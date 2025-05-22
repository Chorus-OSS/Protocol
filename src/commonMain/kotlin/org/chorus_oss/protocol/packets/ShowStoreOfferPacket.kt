package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String

data class ShowStoreOfferPacket(
    val offerID: String,
    val type: Type,
) {
    companion object : PacketCodec<ShowStoreOfferPacket> {
        init { PacketRegistry.register(this) }

        enum class Type {
            Marketplace,
            DressingRoom,
            ServerPage;

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
            get() = ProtocolInfo.SHOW_STORE_OFFER_PACKET

        override fun serialize(value: ShowStoreOfferPacket, stream: Sink) {
            Proto.String.serialize(value.offerID, stream)
            Type.serialize(value.type, stream)
        }

        override fun deserialize(stream: Source): ShowStoreOfferPacket {
            return ShowStoreOfferPacket(
                offerID = Proto.String.deserialize(stream),
                type = Type.deserialize(stream),
            )
        }
    }
}
