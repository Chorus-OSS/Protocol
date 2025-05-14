package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CodeBuilderCodeStatus {
    NONE,
    NOT_STARTED,
    IN_PROGRESS,
    PAUSED,
    ERROR,
    SUCCEEDED;

    companion object : ProtoCodec<CodeBuilderCodeStatus> {
        override fun serialize(value: CodeBuilderCodeStatus, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): CodeBuilderCodeStatus {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}