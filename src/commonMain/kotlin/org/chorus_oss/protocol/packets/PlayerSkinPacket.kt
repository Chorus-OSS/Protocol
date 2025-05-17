package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.skin.Skin
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
class PlayerSkinPacket(
    val uuid: Uuid,
    val skin: Skin,
    val newSkinName: String,
    val oldSkinName: String,
    val isTrusted: Boolean,
) : Packet(id) {
    companion object : PacketCodec<PlayerSkinPacket> {
        override val id: Int
            get() = ProtocolInfo.PLAYER_SKIN_PACKET

        override fun serialize(value: PlayerSkinPacket, stream: Sink) {
            Proto.Uuid.serialize(value.uuid, stream)
            Skin.serialize(value.skin, stream)
            Proto.String.serialize(value.newSkinName, stream)
            Proto.String.serialize(value.oldSkinName, stream)
            Proto.Boolean.serialize(value.isTrusted, stream)
        }

        override fun deserialize(stream: Source): PlayerSkinPacket {
            return PlayerSkinPacket(
                uuid = Proto.Uuid.deserialize(stream),
                skin = Skin.deserialize(stream),
                newSkinName = Proto.String.deserialize(stream),
                oldSkinName = Proto.String.deserialize(stream),
                isTrusted = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
