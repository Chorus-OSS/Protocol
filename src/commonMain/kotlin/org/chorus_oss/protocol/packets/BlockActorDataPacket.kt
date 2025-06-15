package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3

data class BlockActorDataPacket(
    var blockPosition: IVector3,
    var actorDataTags: CompoundTag
) : Packet(id) {
    companion object : PacketCodec<BlockActorDataPacket> {
        override val id: Int = 56

        override fun deserialize(stream: Source): BlockActorDataPacket {
            return BlockActorDataPacket(
                blockPosition = UIVector3.deserialize(stream),
                actorDataTags = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
            )
        }

        override fun serialize(value: BlockActorDataPacket, stream: Sink) {
            UIVector3.serialize(value.blockPosition, stream)
            Tag.serialize(value.actorDataTags, stream, TagSerialization.NetLE, true)
        }
    }
}
