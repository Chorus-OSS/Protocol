package org.chorus_oss.protocol.packets

import org.chorus_oss.protocol.types.Vector3f

class UpdateClientInputLocksPacket : Packet(id) {
    var lockComponentData: Int = 0
    lateinit var serverPosition: Vector3f

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(lockComponentData)
        byteBuf.writeVector3f(serverPosition)
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_CLIENT_INPUT_LOCKS
    }




    companion object : PacketCodec<UpdateClientInputLocksPacket> {
        override fun deserialize(stream: Source): UpdateClientInputLocksPacket {
            val packet = UpdateClientInputLocksPacket()

            packet.lockComponentData = byteBuf.readVarInt()
            packet.serverPosition = Vector3f.deserialize(stream)

            return packet
        }
    }
}
