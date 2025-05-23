package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*

data class ResourcePackDataInfoPacket(
    val resourceName: String,
    val chunkSize: UInt,
    val chunkCount: UInt,
    val fileSize: ULong,
    val fileHash: String,
    val premium: Boolean,
    val type: Type,
) {
    companion object : PacketCodec<ResourcePackDataInfoPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class Type {
            Invalid,
            Addon,
            Cached,
            CopyProtected,
            Behaviour,
            PersonaPiece,
            Resource,
            Skins,
            WorldTemplate;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return Type.entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.RESOURCE_PACK_DATA_INFO_PACKET

        override fun serialize(value: ResourcePackDataInfoPacket, stream: Sink) {
            Proto.String.serialize(value.resourceName, stream)
            ProtoLE.UInt.serialize(value.chunkSize, stream)
            ProtoLE.UInt.serialize(value.chunkCount, stream)
            ProtoLE.ULong.serialize(value.fileSize, stream)
            Proto.String.serialize(value.fileHash, stream)
            Proto.Boolean.serialize(value.premium, stream)
            Type.serialize(value.type, stream)
        }

        override fun deserialize(stream: Source): ResourcePackDataInfoPacket {
            return ResourcePackDataInfoPacket(
                resourceName = Proto.String.deserialize(stream),
                chunkSize = ProtoLE.UInt.deserialize(stream),
                chunkCount = ProtoLE.UInt.deserialize(stream),
                fileSize = ProtoLE.ULong.deserialize(stream),
                fileHash = Proto.String.deserialize(stream),
                premium = Proto.Boolean.deserialize(stream),
                type = Type.deserialize(stream),
            )
        }
    }
}
