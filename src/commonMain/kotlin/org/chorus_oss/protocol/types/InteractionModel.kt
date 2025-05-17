package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt

enum class InteractionModel {
    Touch,
    Crosshair,
    Classic;

    companion object : ProtoCodec<InteractionModel> {
        override fun serialize(value: InteractionModel, stream: Sink) {
            ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
        }

        override fun deserialize(stream: Source): InteractionModel {
            return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
        }
    }
}