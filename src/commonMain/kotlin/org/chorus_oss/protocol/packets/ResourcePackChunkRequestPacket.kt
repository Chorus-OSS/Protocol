package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt

data class ResourcePackChunkRequestPacket(
    val resourceName: String,
    val chunkID: UInt,
) {
    companion object : PacketCodec<ResourcePackChunkRequestPacket> {
        override val id: Int = 84

        override fun serialize(value: ResourcePackChunkRequestPacket, stream: Sink) {
            Proto.String.serialize(value.resourceName, stream)
            ProtoLE.UInt.serialize(value.chunkID, stream)
        }

        override fun deserialize(stream: Source): ResourcePackChunkRequestPacket {
            return ResourcePackChunkRequestPacket(
                resourceName = Proto.String.deserialize(stream),
                chunkID = ProtoLE.UInt.deserialize(stream),
            )
        }
    }
}
