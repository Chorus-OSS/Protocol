package org.chorus_oss.protocol.types.item

import kotlinx.io.Buffer
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.ProtoCodec

data class ItemStack(
    val netID: Int,
    val metadata: UInt,
    val blockRuntimeID: Int,
    val count: UShort,
    val nbtData: CompoundTag,
    val canBePlacedOn: List<String>,
    val canDestroy: List<String>,
    val hasNetworkID: Boolean,
) {
    companion object : ProtoCodec<ItemStack> {
        override fun serialize(value: ItemStack, stream: Buffer) {
            TODO("Not yet implemented")
        }

        override fun deserialize(stream: Buffer): ItemStack {
            TODO("Not yet implemented")
        }
    }
}
