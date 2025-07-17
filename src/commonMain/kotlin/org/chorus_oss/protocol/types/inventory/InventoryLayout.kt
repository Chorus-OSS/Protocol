package org.chorus_oss.protocol.types.inventory

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class InventoryLayout {
    None,
    Survival,
    RecipeBook,
    Creative;

    companion object : ProtoCodec<InventoryLayout> {
        override fun serialize(
            value: InventoryLayout,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): InventoryLayout {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}
