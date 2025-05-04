package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class EmotePacket : DataPacket() {
    var runtimeId: Long = 0
    var xuid: String = ""
    var platformId: String = ""
    var emoteID: String? = null
    var flags: Byte = 0
    var emoteDuration: Int = 0

    override fun pid(): Int {
        return ProtocolInfo.EMOTE_PACKET
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.runtimeId)
        byteBuf.writeString(emoteID!!)
        byteBuf.writeUnsignedVarInt(this.emoteDuration)
        byteBuf.writeString(this.xuid)
        byteBuf.writeString(this.platformId)
        byteBuf.writeByte(flags.toInt())
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<EmotePacket> {
        override fun decode(byteBuf: ByteBuf): EmotePacket {
            val packet = EmotePacket()

            packet.runtimeId = byteBuf.readActorRuntimeID()
            packet.emoteID = byteBuf.readString()
            packet.emoteDuration = byteBuf.readUnsignedVarInt()
            packet.xuid = byteBuf.readString()
            packet.platformId = byteBuf.readString()
            packet.flags = byteBuf.readByte()

            return packet
        }
    }
}
