package org.chorus_oss.protocol.types.item

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec

data class ItemType(
    val netID: Int,
    val metadata: UInt,
) {
    companion object : ProtoCodec<ItemType> {
        override fun serialize(value: ItemType, stream: Buffer) {
            TODO("Not yet implemented")
        }

        override fun deserialize(stream: Buffer): ItemType {
            TODO("Not yet implemented")
        }
    }
}
