package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class StackResourcePack(
    val uuid: String,
    val version: String,
    val subPackName: String,
) {
    companion object : ProtoCodec<StackResourcePack> {
        override fun serialize(value: StackResourcePack, stream: Sink) {
            Proto.String.serialize(value.uuid, stream)
            Proto.String.serialize(value.version, stream)
            Proto.String.serialize(value.subPackName, stream)
        }

        override fun deserialize(stream: Source): StackResourcePack {
            return StackResourcePack(
                uuid = Proto.String.deserialize(stream),
                version = Proto.String.deserialize(stream),
                subPackName = Proto.String.deserialize(stream),
            )
        }
    }
}
