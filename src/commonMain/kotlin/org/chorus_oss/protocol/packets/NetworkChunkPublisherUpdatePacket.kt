package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3


class NetworkChunkPublisherUpdatePacket : Packet(id) {
    var position: BlockVector3? = null
    var radius: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeSignedBlockPosition(position!!)
        byteBuf.writeUnsignedVarInt(radius)
        byteBuf.writeInt(0) // Saved chunks
    }

    override fun pid(): Int {
        return ProtocolInfo.NETWORK_CHUNK_PUBLISHER_UPDATE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<NetworkChunkPublisherUpdatePacket> {
        override fun decode(byteBuf: ByteBuf): NetworkChunkPublisherUpdatePacket {
            val packet = NetworkChunkPublisherUpdatePacket()

            packet.position = byteBuf.readSignedBlockPosition()
            packet.radius = byteBuf.readUnsignedVarInt()

            return packet
        }
    }
}
