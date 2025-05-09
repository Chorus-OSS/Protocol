package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
val Proto.Uuid by lazy {
    object : ProtoCodec<Uuid> {
        override fun serialize(value: Uuid, stream: Buffer) {
            value.toLongs { most, least ->
                ProtoLE.Long.serialize(most, stream)
                ProtoLE.Long.serialize(least, stream)
            }
        }

        override fun deserialize(stream: Buffer): Uuid {
            val most = ProtoLE.Long.deserialize(stream)
            val least = ProtoLE.Long.deserialize(stream)
            return Uuid.fromLongs(most, least)
        }
    }
}