package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorUniqueID


data class UpdateEquipmentPacket(
    val windowID: Byte,
    val windowType: Byte,
    val size: Int,
    val entityUniqueID: ActorUniqueID,
    val serializedInventoryData: ByteString,
) : Packet(id) {
    companion object : PacketCodec<UpdateEquipmentPacket> {
        override val id: Int = 81

        override fun serialize(value: UpdateEquipmentPacket, stream: Sink) {
            Proto.Byte.serialize(value.windowID, stream)
            Proto.Byte.serialize(value.windowType, stream)
            ProtoVAR.Int.serialize(value.size, stream)
            ActorUniqueID.serialize(value.entityUniqueID, stream)
            Proto.ByteString.serialize(value.serializedInventoryData, stream)
        }

        override fun deserialize(stream: Source): UpdateEquipmentPacket {
            return UpdateEquipmentPacket(
                windowID = Proto.Byte.deserialize(stream),
                windowType = Proto.Byte.deserialize(stream),
                size = ProtoVAR.Int.deserialize(stream),
                entityUniqueID = ActorUniqueID.deserialize(stream),
                serializedInventoryData = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
