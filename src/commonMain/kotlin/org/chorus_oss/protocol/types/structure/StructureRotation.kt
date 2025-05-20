package org.chorus_oss.protocol.types.structure

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class StructureRotation {
    None,
    Rotate90,
    Rotate180,
    Rotate270;

    companion object : ProtoCodec<StructureRotation> {
        override fun serialize(
            value: StructureRotation,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): StructureRotation {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
