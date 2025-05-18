package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.TexturePackInfo
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
data class ResourcePacksInfoPacket(
    val texturePackRequired: Boolean,
    val hasAddons: Boolean,
    val hasScripts: Boolean,
    val worldTemplateUuid: Uuid,
    val worldTemplateVersion: String,
    val texturePacks: List<TexturePackInfo>
) : Packet(id) {
    companion object : PacketCodec<ResourcePacksInfoPacket> {
        override val id: Int
            get() = ProtocolInfo.RESOURCE_PACKS_INFO_PACKET

        override fun serialize(
            value: ResourcePacksInfoPacket,
            stream: Sink
        ) {
            Proto.Boolean.serialize(value.texturePackRequired, stream)
            Proto.Boolean.serialize(value.hasAddons, stream)
            Proto.Boolean.serialize(value.hasScripts, stream)
            Proto.Uuid.serialize(value.worldTemplateUuid, stream)
            Proto.String.serialize(value.worldTemplateVersion, stream)
            value.texturePacks.let { texturePacks ->
                ProtoLE.UShort.serialize(texturePacks.size.toUShort(), stream)
                texturePacks.forEach { TexturePackInfo.serialize(it, stream) }
            }
        }

        override fun deserialize(stream: Source): ResourcePacksInfoPacket {
            return ResourcePacksInfoPacket(
                texturePackRequired = Proto.Boolean.deserialize(stream),
                hasAddons = Proto.Boolean.deserialize(stream),
                hasScripts = Proto.Boolean.deserialize(stream),
                worldTemplateUuid = Proto.Uuid.deserialize(stream),
                worldTemplateVersion = Proto.String.deserialize(stream),
                texturePacks = List(ProtoLE.UShort.deserialize(stream).toInt()) {
                    TexturePackInfo.deserialize(stream)
                },
            )
        }
    }
}
