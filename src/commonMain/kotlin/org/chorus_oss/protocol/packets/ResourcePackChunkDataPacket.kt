package org.chorus_oss.protocol.packets


import kotlinx.io.Buffer
import kotlinx.io.readByteArray
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.*

import java.util.*

data class ResourcePackChunkDataPacket(
    val resourceName: String,
    val chunkID: UInt,
    val byteOffset: ULong,
    val chunkData: String,
) {
    companion object : PacketCodec<ResourcePackChunkDataPacket> {
        override val id: Int
            get() = ProtocolInfo.RESOURCE_PACK_CHUNK_DATA_PACKET

        override fun deserialize(stream: Buffer): ResourcePackChunkDataPacket {
            return ResourcePackChunkDataPacket(
                resourceName = Proto.String.deserialize(stream),
                chunkID = ProtoLE.UInt.deserialize(stream),
                byteOffset = ProtoLE.ULong.deserialize(stream),
                chunkData = Proto.String.deserialize(stream)
            )
        }

        override fun serialize(value: ResourcePackChunkDataPacket, stream: Buffer) {
            Proto.String.serialize(value.resourceName, stream)
            ProtoLE.UInt.serialize(value.chunkID, stream)
            ProtoLE.ULong.serialize(value.byteOffset, stream)
            Proto.String.serialize(value.chunkData, stream)
        }
    }
}
