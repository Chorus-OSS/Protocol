package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.String

data class PhotoTransferPacket(
    val photoName: String,
    val photoData: ByteString,
    val bookID: String,
    val photoType: PhotoType,
    val sourceType: PhotoType,
    val ownerEntityUniqueID: Long,
    val newPhotoName: String,
) : Packet(id) {
    companion object : PacketCodec<PhotoTransferPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class PhotoType {
            Portfolio,
            PhotoItem,
            Book;

            companion object : ProtoCodec<PhotoType> {
                override fun serialize(
                    value: PhotoType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): PhotoType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.PHOTO_TRANSFER_PACKET

        override fun serialize(value: PhotoTransferPacket, stream: Sink) {
            Proto.String.serialize(value.photoName, stream)
            Proto.ByteString.serialize(value.photoData, stream)
            Proto.String.serialize(value.bookID, stream)
            PhotoType.serialize(value.photoType, stream)
            PhotoType.serialize(value.sourceType, stream)
            ProtoLE.Long.serialize(value.ownerEntityUniqueID, stream)
            Proto.String.serialize(value.newPhotoName, stream)
        }

        override fun deserialize(stream: Source): PhotoTransferPacket {
            return PhotoTransferPacket(
                photoName = Proto.String.deserialize(stream),
                photoData = Proto.ByteString.deserialize(stream),
                bookID = Proto.String.deserialize(stream),
                photoType = PhotoType.deserialize(stream),
                sourceType = PhotoType.deserialize(stream),
                ownerEntityUniqueID = ProtoLE.Long.deserialize(stream),
                newPhotoName = Proto.String.deserialize(stream),
            )
        }
    }
}
