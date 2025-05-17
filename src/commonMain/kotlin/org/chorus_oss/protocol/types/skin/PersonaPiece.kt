package org.chorus_oss.protocol.types.skin

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String

data class PersonaPiece(
    val pieceID: String,
    val pieceType: String,
    val packID: String,
    val default: Boolean,
    val productID: String,
) {
    companion object : ProtoCodec<PersonaPiece> {
        override fun serialize(value: PersonaPiece, stream: Sink) {
            Proto.String.serialize(value.pieceID, stream)
            Proto.String.serialize(value.pieceType, stream)
            Proto.String.serialize(value.packID, stream)
            Proto.Boolean.serialize(value.default, stream)
            Proto.String.serialize(value.productID, stream)
        }

        override fun deserialize(stream: Source): PersonaPiece {
            return PersonaPiece(
                pieceID = Proto.String.deserialize(stream),
                pieceType = Proto.String.deserialize(stream),
                packID = Proto.String.deserialize(stream),
                default = Proto.Boolean.deserialize(stream),
                productID = Proto.String.deserialize(stream),
            )
        }
    }
}
