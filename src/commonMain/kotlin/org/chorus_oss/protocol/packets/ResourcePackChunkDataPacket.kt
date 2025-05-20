package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.ULong

data class ResourcePackChunkDataPacket(
    val resourceName: String,
    val chunkID: UInt,
    val byteOffset: ULong,
    val chunkData: String,
) {
    companion object : PacketCodec<ResourcePackChunkDataPacket> {
        override val id: Int
            get() = ProtocolInfo.RESOURCE_PACK_CHUNK_DATA_PACKET

        override fun serialize(value: ResourcePackChunkDataPacket, stream: Sink) {
            Proto.String.serialize(value.resourceName, stream)
            ProtoLE.UInt.serialize(value.chunkID, stream)
            ProtoLE.ULong.serialize(value.byteOffset, stream)
            Proto.String.serialize(value.chunkData, stream)
        }

        override fun deserialize(stream: Source): ResourcePackChunkDataPacket {
            return ResourcePackChunkDataPacket(
                resourceName = Proto.String.deserialize(stream),
                chunkID = ProtoLE.UInt.deserialize(stream),
                byteOffset = ProtoLE.ULong.deserialize(stream),
                chunkData = Proto.String.deserialize(stream),
            )
        }
    }
}
