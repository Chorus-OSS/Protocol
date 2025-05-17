package org.chorus_oss.protocol.types.item

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.EnchantmentInstance

data class ItemEnchantments(
    val slot: Int,
    val enchantmentSlice1: List<EnchantmentInstance>,
    val enchantmentSlice2: List<EnchantmentInstance>,
    val enchantmentSlice3: List<EnchantmentInstance>,
) {
    companion object : ProtoCodec<ItemEnchantments> {
        override fun serialize(value: ItemEnchantments, stream: Sink) {
            ProtoLE.Int.serialize(value.slot, stream)
            ProtoHelper.serializeList(value.enchantmentSlice1, stream, EnchantmentInstance)
            ProtoHelper.serializeList(value.enchantmentSlice2, stream, EnchantmentInstance)
            ProtoHelper.serializeList(value.enchantmentSlice3, stream, EnchantmentInstance)
        }

        override fun deserialize(stream: Source): ItemEnchantments {
            return ItemEnchantments(
                slot = ProtoLE.Int.deserialize(stream),
                enchantmentSlice1 = ProtoHelper.deserializeList(stream, EnchantmentInstance),
                enchantmentSlice2 = ProtoHelper.deserializeList(stream, EnchantmentInstance),
                enchantmentSlice3 = ProtoHelper.deserializeList(stream, EnchantmentInstance),
            )
        }
    }
}
