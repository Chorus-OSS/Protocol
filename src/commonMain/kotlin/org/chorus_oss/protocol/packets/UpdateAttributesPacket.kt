package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.attribute.Attribute


data class UpdateAttributesPacket(
    val actorRuntimeID: ActorRuntimeID,
    val attributes: List<Attribute>,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<UpdateAttributesPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.UPDATE_ATTRIBUTES_PACKET

        override fun serialize(value: UpdateAttributesPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            ProtoHelper.serializeList(value.attributes, stream, Attribute)
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): UpdateAttributesPacket {
            return UpdateAttributesPacket(
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                attributes = ProtoHelper.deserializeList(stream, Attribute),
                tick = ProtoVAR.ULong.deserialize(stream),
            )
        }
    }
}
