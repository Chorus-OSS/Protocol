package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.ULong

data class ResourcePackChunkDataPacket(
    val resourceName: String,
    val chunkID: UInt,
    val byteOffset: ULong,
    val chunkData: ByteString,
) : Packet(id) {
    companion object : PacketCodec<ResourcePackChunkDataPacket> {
        override val id: Int = 83

        override fun serialize(value: ResourcePackChunkDataPacket, stream: Sink) {
            Proto.String.serialize(value.resourceName, stream)
            ProtoLE.UInt.serialize(value.chunkID, stream)
            ProtoLE.ULong.serialize(value.byteOffset, stream)
            Proto.ByteString.serialize(value.chunkData, stream)
        }

        override fun deserialize(stream: Source): ResourcePackChunkDataPacket {
            return ResourcePackChunkDataPacket(
                resourceName = Proto.String.deserialize(stream),
                chunkID = ProtoLE.UInt.deserialize(stream),
                byteOffset = ProtoLE.ULong.deserialize(stream),
                chunkData = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
