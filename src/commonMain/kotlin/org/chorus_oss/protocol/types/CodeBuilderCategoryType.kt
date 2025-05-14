package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CodeBuilderCategoryType {
    NONE,
    CODE_STATUS,
    INSTANTIATION;

    companion object : ProtoCodec<CodeBuilderCategoryType> {
        override fun serialize(value: CodeBuilderCategoryType, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): CodeBuilderCategoryType {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
