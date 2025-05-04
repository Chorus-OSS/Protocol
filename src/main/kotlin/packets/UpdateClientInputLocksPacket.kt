package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.Vector3f

import org.chorus_oss.protocol.ProtocolInfo

class UpdateClientInputLocksPacket : DataPacket() {
    var lockComponentData: Int = 0
    lateinit var serverPosition: Vector3f

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(lockComponentData)
        byteBuf.writeVector3f(serverPosition)
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_CLIENT_INPUT_LOCKS
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }


    companion object : PacketDecoder<UpdateClientInputLocksPacket> {
        override fun decode(byteBuf: ByteBuf): UpdateClientInputLocksPacket {
            val packet = UpdateClientInputLocksPacket()

            packet.lockComponentData = byteBuf.readVarInt()
            packet.serverPosition = byteBuf.readVector3f()

            return packet
        }
    }
}
