package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.utils.Version
import org.chorus_oss.protocol.ProtocolInfo

import java.util.*

class ResourcePackDataInfoPacket : AbstractResourcePackDataPacket() {
    override var packId: UUID? = null
    override var packVersion: Version? = null
    var maxChunkSize: Int = 0
    var chunkCount: Int = 0
    var compressedPackSize: Long = 0
    var sha256: ByteArray = byteArrayOf()
    var premium: Boolean = false
    var type: Int = TYPE_RESOURCE

    override fun encode(byteBuf: ByteBuf) {
        encodePackInfo(byteBuf)
        byteBuf.writeIntLE(this.maxChunkSize)
        byteBuf.writeIntLE(this.chunkCount)
        byteBuf.writeLongLE(this.compressedPackSize)
        byteBuf.writeByteArray(this.sha256)
        byteBuf.writeBoolean(this.premium)
        byteBuf.writeByte(type.toByte().toInt())
    }

    override fun pid(): Int {
        return ProtocolInfo.RESOURCE_PACK_DATA_INFO_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ResourcePackDataInfoPacket> {
        override fun decode(byteBuf: ByteBuf): ResourcePackDataInfoPacket {
            val packet = ResourcePackDataInfoPacket()

            packet.decodePackInfo(byteBuf)
            packet.maxChunkSize = byteBuf.readIntLE()
            packet.chunkCount = byteBuf.readIntLE()
            packet.compressedPackSize = byteBuf.readLongLE()
            packet.sha256 = byteBuf.readByteArray()
            packet.premium = byteBuf.readBoolean()
            packet.type = byteBuf.readByte().toInt()

            return packet
        }

        const val TYPE_INVALID: Int = 0
        const val TYPE_ADDON: Int = 1
        const val TYPE_CACHED: Int = 2
        const val TYPE_COPY_PROTECTED: Int = 3
        const val TYPE_BEHAVIOR: Int = 4
        const val TYPE_PERSONA_PIECE: Int = 5
        const val TYPE_RESOURCE: Int = 6
        const val TYPE_SKINS: Int = 7
        const val TYPE_WORLD_TEMPLATE: Int = 8
        const val TYPE_COUNT: Int = 9
    }
}
