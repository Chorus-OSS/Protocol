package org.chorus_oss.protocol.packets


import org.chorus_oss.chorus.utils.UUIDValidator

import java.util.*


class ResourcePackClientResponsePacket : Packet(id) {
    var responseStatus: Byte = 0
    var packEntries: Array<Entry> = emptyArray()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(responseStatus.toInt())
        byteBuf.writeShortLE(packEntries.size)
        for (entry in this.packEntries) {
            byteBuf.writeString(entry.uuid.toString() + '_' + entry.version)
        }
    }


    class Entry(val uuid: UUID, val version: String)

    override fun pid(): Int {
        return ProtocolInfo.RESOURCE_PACK_CLIENT_RESPONSE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ResourcePackClientResponsePacket> {
        override fun decode(byteBuf: ByteBuf): ResourcePackClientResponsePacket {
            val packet = ResourcePackClientResponsePacket()

            packet.responseStatus = byteBuf.readByte()
            packet.packEntries = Array(byteBuf.readShortLE().toInt()) {
                val entry = byteBuf.readString().split("_")

                if (UUIDValidator.isValidUUID(entry[0])) {
                    Entry(
                        UUID.fromString(
                            entry[0]
                        ), entry[1]
                    )
                } else throw RuntimeException("Invalid UUID format")
            }

            return packet
        }

        const val STATUS_REFUSED: Byte = 1
        const val STATUS_SEND_PACKS: Byte = 2
        const val STATUS_HAVE_ALL_PACKS: Byte = 3
        const val STATUS_COMPLETED: Byte = 4
    }
}
