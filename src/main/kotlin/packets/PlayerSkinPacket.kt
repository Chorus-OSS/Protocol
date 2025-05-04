package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.Server
import org.chorus_oss.chorus.entity.data.Skin

import org.chorus_oss.protocol.ProtocolInfo

import java.util.*


class PlayerSkinPacket : DataPacket() {
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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PlayerSkinPacket> {
        override fun decode(byteBuf: ByteBuf): PlayerSkinPacket {
            val packet = PlayerSkinPacket()

            packet.uuid = byteBuf.readUUID()
            packet.skin = byteBuf.readSkin()
            packet.newSkinName = byteBuf.readString()
            packet.oldSkinName = byteBuf.readString()
            if (byteBuf.isReadable) { // -facepalm-
                packet.skin.setTrusted(byteBuf.readBoolean())
            }

            return packet
        }
    }
}
