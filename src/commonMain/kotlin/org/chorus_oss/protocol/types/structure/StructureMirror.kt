package org.chorus_oss.protocol.types.structure

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class StructureMirror {
    None,
    X,
    Z,
    XZ;

    companion object : ProtoCodec<StructureMirror> {
        override fun serialize(
            value: StructureMirror,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): StructureMirror {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
