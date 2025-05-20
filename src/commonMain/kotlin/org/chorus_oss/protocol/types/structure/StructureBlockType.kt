package org.chorus_oss.protocol.types.structure

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class StructureBlockType {
    Data,
    Save,
    Load,
    Corner,
    Invalid,
    Export;

    companion object : ProtoCodec<StructureBlockType> {
        override fun serialize(
            value: StructureBlockType,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): StructureBlockType {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}