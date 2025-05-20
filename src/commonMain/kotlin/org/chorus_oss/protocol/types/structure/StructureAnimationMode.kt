package org.chorus_oss.protocol.types.structure

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class StructureAnimationMode {
    None,
    Layer,
    Blocks;

    companion object : ProtoCodec<StructureAnimationMode> {
        override fun serialize(
            value: StructureAnimationMode,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): StructureAnimationMode {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
