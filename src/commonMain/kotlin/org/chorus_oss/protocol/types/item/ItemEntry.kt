package org.chorus_oss.protocol.types.item

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Short
import org.chorus_oss.protocol.core.types.String

data class ItemEntry(
    val name: String,
    val runtimeID: Short,
    val componentBased: Boolean,
    val version: Int,
    val data: CompoundTag
) {
    companion object : ProtoCodec<ItemEntry> {
        override fun serialize(value: ItemEntry, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            ProtoLE.Short.serialize(value.runtimeID, stream)
            Proto.Boolean.serialize(value.componentBased, stream)
            ProtoVAR.Int.serialize(value.version, stream)
            Tag.serialize(value.data, stream, TagSerialization.NetLE, true)
        }

        override fun deserialize(stream: Source): ItemEntry {
            return ItemEntry(
                name = Proto.String.deserialize(stream),
                runtimeID = ProtoLE.Short.deserialize(stream),
                componentBased = Proto.Boolean.deserialize(stream),
                version = ProtoVAR.Int.deserialize(stream),
                data = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag
            )
        }
    }
}
