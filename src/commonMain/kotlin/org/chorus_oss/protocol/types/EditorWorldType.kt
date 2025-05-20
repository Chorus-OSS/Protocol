package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class EditorWorldType {
    NotEditor,
    Project,
    TestLevel,
    RealmsUpload;

    companion object : ProtoCodec<EditorWorldType> {
        override fun serialize(value: EditorWorldType, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): EditorWorldType {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}