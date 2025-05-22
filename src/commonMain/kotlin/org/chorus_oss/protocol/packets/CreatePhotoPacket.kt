package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.String


data class CreatePhotoPacket(
    val rawID: Long,
    val photoName: String,
    val photoItemName: String,
) : Packet(id) {
    companion object : PacketCodec<CreatePhotoPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.CREATE_PHOTO_PACKET

        override fun serialize(value: CreatePhotoPacket, stream: Sink) {
            ProtoLE.Long.serialize(value.rawID, stream)
            Proto.String.serialize(value.photoName, stream)
            Proto.String.serialize(value.photoItemName, stream)
        }

        override fun deserialize(stream: Source): CreatePhotoPacket {
            return CreatePhotoPacket(
                rawID = ProtoLE.Long.deserialize(stream),
                photoName = Proto.String.deserialize(stream),
                photoItemName = Proto.String.deserialize(stream)
            )
        }
    }
}
