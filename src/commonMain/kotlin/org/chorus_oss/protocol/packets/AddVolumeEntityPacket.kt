package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos

data class AddVolumeEntityPacket(
    val entityRuntimeID: UInt,
    val components: CompoundTag,
    val jsonIdentifier: String,
    val instanceIdentifier: String,
    val minBounds: BlockPos,
    val maxBounds: BlockPos,
    val dimension: Int,
    val engineVersion: String,
) : Packet(id) {
    companion object : PacketCodec<AddVolumeEntityPacket> {
        override val id: Int = 166

        override fun deserialize(stream: Source): AddVolumeEntityPacket {
            return AddVolumeEntityPacket(
                entityRuntimeID = ProtoVAR.UInt.deserialize(stream),
                components = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
                jsonIdentifier = Proto.String.deserialize(stream),
                instanceIdentifier = Proto.String.deserialize(stream),
                minBounds = NetBlockPos.deserialize(stream),
                maxBounds = NetBlockPos.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
                engineVersion = Proto.String.deserialize(stream),
            )
        }

        override fun serialize(value: AddVolumeEntityPacket, stream: Sink) {
            ProtoVAR.UInt.serialize(value.entityRuntimeID, stream)
            Tag.serialize(value.components, stream, TagSerialization.NetLE, true)
            Proto.String.serialize(value.jsonIdentifier, stream)
            Proto.String.serialize(value.instanceIdentifier, stream)
            NetBlockPos.serialize(value.minBounds, stream)
            NetBlockPos.serialize(value.maxBounds, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            Proto.String.serialize(value.engineVersion, stream)
        }
    }
}
