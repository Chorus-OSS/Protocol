package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.Server
import org.chorus_oss.chorus.entity.data.Skin

import java.util.*


class PlayerSkinPacket : Packet(id) {
    lateinit var uuid: UUID
    lateinit var skin: Skin
    lateinit var newSkinName: String
    lateinit var oldSkinName: String

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUUID(uuid)
        byteBuf.writeSkin(skin)
        byteBuf.writeString(newSkinName)
        byteBuf.writeString(oldSkinName)
        byteBuf.writeBoolean(skin.isTrusted() || Server.instance.settings.playerSettings.forceSkinTrusted)
    }

    override fun pid(): Int {
        return ProtocolInfo.PLAYER_SKIN_PACKET
    }



    companion object : PacketCodec<PlayerSkinPacket> {
        override fun deserialize(stream: Source): PlayerSkinPacket {
            val packet = PlayerSkinPacket()

            packet.uuid = byteBuf.readUUID()
            packet.skin = byteBuf.readSkin()
            packet.newSkinName = Proto.String.deserialize(stream)
            packet.oldSkinName = Proto.String.deserialize(stream)
            if (byteBuf.isReadable) { // -facepalm-
                packet.skin.setTrusted(Proto.Boolean.deserialize(stream))
            }

            return packet
        }
    }
}
