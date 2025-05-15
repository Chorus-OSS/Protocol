package org.chorus_oss.protocol.types.creative

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.item.ItemInstance

data class CreativeItem(
    val creativeItemNetworkID: UInt,
    val item: ItemInstance,
    val groupIndex: UInt
) {
    companion object : ProtoCodec<CreativeItem> {
        override fun serialize(value: CreativeItem, stream: Sink) {
            ProtoVAR.UInt.serialize(value.creativeItemNetworkID, stream)
            ItemInstance.serialize(value.item, stream)
            ProtoVAR.UInt.serialize(value.groupIndex, stream)
        }

        override fun deserialize(stream: Source): CreativeItem {
            return CreativeItem(
                creativeItemNetworkID = ProtoVAR.UInt.deserialize(stream),
                item = ItemInstance.deserialize(stream),
                groupIndex = ProtoVAR.UInt.deserialize(stream)
            )
        }
    }
}
