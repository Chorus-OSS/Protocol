package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.item.ItemEnchantments

data class EnchantmentOption(
    val cost: UInt,
    val enchantments: ItemEnchantments,
    val name: String,
    val recipeNetworkID: UInt
) {
    companion object : ProtoCodec<EnchantmentOption> {
        override fun serialize(value: EnchantmentOption, stream: Sink) {
            ProtoVAR.UInt.serialize(value.cost, stream)
            ItemEnchantments.serialize(value.enchantments, stream)
            Proto.String.serialize(value.name, stream)
            ProtoVAR.UInt.serialize(value.recipeNetworkID, stream)
        }

        override fun deserialize(stream: Source): EnchantmentOption {
            return EnchantmentOption(
                cost = ProtoVAR.UInt.deserialize(stream),
                enchantments = ItemEnchantments.deserialize(stream),
                name = Proto.String.deserialize(stream),
                recipeNetworkID = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
