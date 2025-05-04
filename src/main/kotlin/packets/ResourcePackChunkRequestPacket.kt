package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.utils.Version
import org.chorus_oss.protocol.ProtocolInfo

import java.util.*


class ResourcePackChunkRequestPacket : AbstractResourcePackDataPacket() {
    override var packId: UUID? = null
    override var packVersion: Version? = null
    var chunkIndex: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        encodePackInfo(byteBuf)
        byteBuf.writeIntLE(this.chunkIndex)
    }

    override fun pid(): Int {
        return ProtocolInfo.RESOURCE_PACK_CHUNK_REQUEST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ResourcePackChunkRequestPacket> {
        override fun decode(byteBuf: ByteBuf): ResourcePackChunkRequestPacket {
            val packet = ResourcePackChunkRequestPacket()

            packet.decodePackInfo(byteBuf)
            packet.chunkIndex = byteBuf.readIntLE()

            return packet
        }
    }
}
