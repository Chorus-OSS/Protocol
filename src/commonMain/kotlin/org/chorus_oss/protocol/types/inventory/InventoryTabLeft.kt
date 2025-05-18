package org.chorus_oss.protocol.types.inventory

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class InventoryTabLeft {
    None,
    Construction,
    Equipment,
    Items,
    Nature,
    Search,
    Survival;

    companion object : ProtoCodec<InventoryTabLeft> {
        override fun serialize(
            value: InventoryTabLeft,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): InventoryTabLeft {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
