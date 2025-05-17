package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte


data class LegacySetItemSlotData(
    val containerID: Byte,
    val slots: List<Byte>,
) {
    companion object : ProtoCodec<LegacySetItemSlotData> {
        override fun serialize(value: LegacySetItemSlotData, stream: Sink) {
            Proto.Byte.serialize(value.containerID, stream)
            ProtoHelper.serializeList(value.slots, stream, Proto.Byte)
        }

        override fun deserialize(stream: Source): LegacySetItemSlotData {
            return LegacySetItemSlotData(
                containerID = Proto.Byte.deserialize(stream),
                slots = ProtoHelper.deserializeList(stream, Proto.Byte)
            )
        }
    }
}
