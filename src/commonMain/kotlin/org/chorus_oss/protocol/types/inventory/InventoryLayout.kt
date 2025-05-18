package org.chorus_oss.protocol.types.inventory

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

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
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): InventoryLayout {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
