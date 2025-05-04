package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.utils.Version
import org.chorus_oss.protocol.ProtocolInfo

import java.util.*

class ResourcePackChunkDataPacket : AbstractResourcePackDataPacket() {
    override var packId: UUID? = null
    override var packVersion: Version? = null
    var chunkIndex: Int = 0
    var progress: Long = 0
    var data: ByteArray = ByteArray(0)

    override fun encode(byteBuf: ByteBuf) {
        encodePackInfo(byteBuf)
        byteBuf.writeIntLE(this.chunkIndex)
        byteBuf.writeLongLE(this.progress)
        byteBuf.writeByteArray(this.data)
    }

    override fun pid(): Int {
        return ProtocolInfo.RESOURCE_PACK_CHUNK_DATA_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ResourcePackChunkDataPacket> {
        override fun decode(byteBuf: ByteBuf): ResourcePackChunkDataPacket {
            val packet = ResourcePackChunkDataPacket()

            packet.decodePackInfo(byteBuf)
            packet.chunkIndex = byteBuf.readIntLE()
            packet.progress = byteBuf.readLongLE()
            packet.data = byteBuf.readByteArray()

            return packet
        }
    }
}
