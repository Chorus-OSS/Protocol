package org.chorus_oss.protocol.types.structure

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class StructureRedstoneSaveMode {
    Memory,
    Disk;

    companion object : ProtoCodec<StructureRedstoneSaveMode> {
        override fun serialize(
            value: StructureRedstoneSaveMode,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): StructureRedstoneSaveMode {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}