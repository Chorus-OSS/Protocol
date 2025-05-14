package org.chorus_oss.protocol.packets


import java.util.*


class EmoteListPacket : Packet(id) {
    var runtimeId: Long = 0
    val pieceIds: MutableList<UUID> = mutableListOf()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(runtimeId)
        byteBuf.writeUnsignedVarInt(pieceIds.size)
        for (id in pieceIds) {
            byteBuf.writeUUID(id)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.EMOTE_LIST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<EmoteListPacket> {
        override fun decode(byteBuf: ByteBuf): EmoteListPacket {
            val packet = EmoteListPacket()

            packet.runtimeId = byteBuf.readActorRuntimeID()
            for (i in 0..<byteBuf.readUnsignedVarInt()) {
                val id = byteBuf.readUUID()
                packet.pieceIds.add(id)
            }

            return packet
        }
    }
}
