package org.chorus_oss.protocol.types.skin

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt

data class PersonaPieceTintColor(
    val pieceType: String,
    val colors: List<String>
) {
    companion object : ProtoCodec<PersonaPieceTintColor> {
        override fun serialize(
            value: PersonaPieceTintColor,
            stream: Sink
        ) {
            Proto.String.serialize(value.pieceType, stream)
            ProtoLE.UInt.serialize(value.colors.size.toUInt(), stream)
            value.colors.forEach { Proto.String.serialize(it, stream) }
        }

        override fun deserialize(stream: Source): PersonaPieceTintColor {
            return PersonaPieceTintColor(
                pieceType = Proto.String.deserialize(stream),
                colors = List(ProtoLE.UInt.deserialize(stream).toInt()) {
                    Proto.String.deserialize(stream)
                },
            )
        }
    }
}
