package org.chorus_oss.protocol.types.inventory

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class InventoryTabRight {
    None,
    FullScreen,
    Crafting,
    Armor;

    companion object : ProtoCodec<InventoryTabRight> {
        override fun serialize(
            value: InventoryTabRight,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): InventoryTabRight {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
