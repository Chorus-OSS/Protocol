package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos

data class BlockActorDataPacket(
    var blockPosition: BlockPos,
    var actorDataTags: CompoundTag
) : Packet(id) {
    companion object : PacketCodec<BlockActorDataPacket> {
        override val id: Int = 56

        override fun deserialize(stream: Source): BlockActorDataPacket {
            return BlockActorDataPacket(
                blockPosition = NetBlockPos.deserialize(stream),
                actorDataTags = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
            )
        }

        override fun serialize(value: BlockActorDataPacket, stream: Sink) {
            NetBlockPos.serialize(value.blockPosition, stream)
            Tag.serialize(value.actorDataTags, stream, TagSerialization.NetLE, true)
        }
    }
}
