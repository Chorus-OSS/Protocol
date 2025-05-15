package org.chorus_oss.protocol.types.creative

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.item.ItemInstance

data class CreativeGroup(
    val category: CreativeCategory,
    val name: String,
    val icon: ItemInstance
) {
    companion object : ProtoCodec<CreativeGroup> {
        override fun serialize(value: CreativeGroup, stream: Sink) {
            CreativeCategory.serialize(value.category, stream)
            Proto.String.serialize(value.name, stream)
            ItemInstance.serialize(value.icon, stream)
        }

        override fun deserialize(stream: Source): CreativeGroup {
            return CreativeGroup(
                category = CreativeCategory.deserialize(stream),
                name = Proto.String.deserialize(stream),
                icon = ItemInstance.deserialize(stream)
            )
        }
    }
}
