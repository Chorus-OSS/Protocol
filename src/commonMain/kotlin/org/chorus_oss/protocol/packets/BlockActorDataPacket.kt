package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.shared.types.IVector3
import org.chorus_oss.protocol.shared.types.UIVector3

data class BlockActorDataPacket(
    var blockPosition: IVector3,
    var actorDataTags: CompoundTag
) {
    companion object : PacketCodec<BlockActorDataPacket> {
        override val id: Int
            get() = ProtocolInfo.BLOCK_ACTOR_DATA_PACKET

        override fun deserialize(stream: Buffer): BlockActorDataPacket {
            return BlockActorDataPacket(
                blockPosition = UIVector3.deserialize(stream),
                actorDataTags = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
            )
        }

        override fun serialize(value: BlockActorDataPacket, stream: Buffer) {
            UIVector3.serialize(value.blockPosition, stream)
            Tag.serialize(value.actorDataTags, stream, TagSerialization.NetLE, true)
        }
    }
}
