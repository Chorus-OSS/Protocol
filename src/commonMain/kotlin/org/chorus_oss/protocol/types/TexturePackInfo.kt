package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.core.types.Uuid
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class TexturePackInfo(
    val uuid: Uuid,
    val version: String,
    val size: ULong,
    val contentKey: String,
    val subPackName: String,
    val contentIdentity: String,
    val hasScripts: Boolean,
    val addonPack: Boolean,
    val rtxEnabled: Boolean,
    val downloadURL: String,
) {
    companion object : ProtoCodec<TexturePackInfo> {
        override fun serialize(value: TexturePackInfo, stream: Sink) {
            Proto.Uuid.serialize(value.uuid, stream)
            Proto.String.serialize(value.version, stream)
            ProtoLE.ULong.serialize(value.size, stream)
            Proto.String.serialize(value.contentKey, stream)
            Proto.String.serialize(value.subPackName, stream)
            Proto.String.serialize(value.contentIdentity, stream)
            Proto.Boolean.serialize(value.hasScripts, stream)
            Proto.Boolean.serialize(value.addonPack, stream)
            Proto.Boolean.serialize(value.rtxEnabled, stream)
            Proto.String.serialize(value.downloadURL, stream)
        }

        override fun deserialize(stream: Source): TexturePackInfo {
            return TexturePackInfo(
                uuid = Proto.Uuid.deserialize(stream),
                version = Proto.String.deserialize(stream),
                size = ProtoLE.ULong.deserialize(stream),
                contentKey = Proto.String.deserialize(stream),
                subPackName = Proto.String.deserialize(stream),
                contentIdentity = Proto.String.deserialize(stream),
                hasScripts = Proto.Boolean.deserialize(stream),
                addonPack = Proto.Boolean.deserialize(stream),
                rtxEnabled = Proto.Boolean.deserialize(stream),
                downloadURL = Proto.String.deserialize(stream),
            )
        }
    }
}
