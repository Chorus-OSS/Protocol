package org.chorus_oss.protocol.types.inventory

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

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
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): InventoryTabRight {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}
