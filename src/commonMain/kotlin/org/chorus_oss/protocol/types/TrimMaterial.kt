package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class TrimMaterial(
    val materialID: String,
    val color: String,
    val itemName: String
) {
    companion object : ProtoCodec<TrimMaterial> {
        override fun serialize(value: TrimMaterial, stream: Sink) {
            Proto.String.serialize(value.materialID, stream)
            Proto.String.serialize(value.color, stream)
            Proto.String.serialize(value.itemName, stream)
        }

        override fun deserialize(stream: Source): TrimMaterial {
            return TrimMaterial(
                materialID = Proto.String.deserialize(stream),
                color = Proto.String.deserialize(stream),
                itemName = Proto.String.deserialize(stream),
            )
        }
    }
}
