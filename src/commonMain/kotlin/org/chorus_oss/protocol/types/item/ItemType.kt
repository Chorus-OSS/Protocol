package org.chorus_oss.protocol.types.item

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec

data class ItemType(
    val netID: Int,
    val metadata: UInt,
) {
    companion object : ProtoCodec<ItemType> {
        override fun serialize(value: ItemType, stream: Sink) {
            TODO("Not yet implemented")
        }

        override fun deserialize(stream: Source): ItemType {
            TODO("Not yet implemented")
        }
    }
}
