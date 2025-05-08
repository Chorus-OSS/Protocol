package org.chorus_oss.protocol.types.item

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec

data class ItemInstance(
    val stackNetID: Int,
    val stack: ItemStack,
) {
    companion object : ProtoCodec<ItemInstance> {
        override fun serialize(value: ItemInstance, stream: Buffer) {
            TODO("Not yet implemented")
        }

        override fun deserialize(stream: Buffer): ItemInstance {
            TODO("Not yet implemented")
        }
    }
}
