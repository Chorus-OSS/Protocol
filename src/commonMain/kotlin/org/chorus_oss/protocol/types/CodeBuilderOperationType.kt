package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CodeBuilderOperationType {
    None,
    Get,
    Set,
    Reset;

    companion object : ProtoCodec<CodeBuilderOperationType> {
        override fun serialize(value: CodeBuilderOperationType, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): CodeBuilderOperationType {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
