package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class BlocksEntry(
    val name: String,
    val properties: CompoundTag
) {
    companion object : ProtoCodec<BlocksEntry> {
        override fun serialize(value: BlocksEntry, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            Tag.serialize(value.properties, stream, TagSerialization.NetLE, true)
        }

        override fun deserialize(stream: Source): BlocksEntry {
            return BlocksEntry(
                name = Proto.String.deserialize(stream),
                properties = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
            )
        }
    }
}
