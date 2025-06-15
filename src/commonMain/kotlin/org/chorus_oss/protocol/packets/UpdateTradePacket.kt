package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.types.ActorUniqueID


data class UpdateTradePacket(
    val windowID: Byte,
    val windowType: Byte,
    val size: Int,
    val tradeTier: Int,
    val villagerUniqueID: ActorUniqueID,
    val entityUniqueID: ActorUniqueID,
    val displayName: String,
    val newTradeUI: Boolean,
    val demandBasedPrices: Boolean,
    val serializedOffers: ByteString,
) : Packet(id) {
    companion object : PacketCodec<UpdateTradePacket> {
        override val id: Int = 80

        override fun serialize(value: UpdateTradePacket, stream: Sink) {
            Proto.Byte.serialize(value.windowID, stream)
            Proto.Byte.serialize(value.windowType, stream)
            ProtoVAR.Int.serialize(value.size, stream)
            ProtoVAR.Int.serialize(value.tradeTier, stream)
            ActorUniqueID.serialize(value.villagerUniqueID, stream)
            ActorUniqueID.serialize(value.entityUniqueID, stream)
            Proto.String.serialize(value.displayName, stream)
            Proto.Boolean.serialize(value.newTradeUI, stream)
            Proto.Boolean.serialize(value.demandBasedPrices, stream)
            Proto.ByteString.serialize(value.serializedOffers, stream)
        }

        override fun deserialize(stream: Source): UpdateTradePacket {
            return UpdateTradePacket(
                windowID = Proto.Byte.deserialize(stream),
                windowType = Proto.Byte.deserialize(stream),
                size = ProtoVAR.Int.deserialize(stream),
                tradeTier = ProtoVAR.Int.deserialize(stream),
                villagerUniqueID = ActorUniqueID.deserialize(stream),
                entityUniqueID = ActorUniqueID.deserialize(stream),
                displayName = Proto.String.deserialize(stream),
                newTradeUI = Proto.Boolean.deserialize(stream),
                demandBasedPrices = Proto.Boolean.deserialize(stream),
                serializedOffers = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
