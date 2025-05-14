package org.chorus_oss.protocol.packets


class RespawnPacket : Packet(id) {
    @JvmField
    var x: Float = 0f

    @JvmField
    var y: Float = 0f

    @JvmField
    var z: Float = 0f

    @JvmField
    var respawnState: Int = STATE_SEARCHING_FOR_SPAWN

    @JvmField
    var runtimeEntityId: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVector3f(this.x, this.y, this.z)
        byteBuf.writeByte(respawnState.toByte().toInt())
        byteBuf.writeActorRuntimeID(runtimeEntityId)
    }

    override fun pid(): Int {
        return ProtocolInfo.RESPAWN_PACKET
    }



    companion object : PacketCodec<RespawnPacket> {
        override fun deserialize(stream: Source): RespawnPacket {
            val packet = RespawnPacket()

            val v = Vector3f.deserialize(stream)
            packet.x = v.x
            packet.y = v.y
            packet.z = v.z
            packet.respawnState = Proto.Byte.deserialize(stream).toInt()
            packet.runtimeEntityId = byteBuf.readActorRuntimeID()

            return packet
        }

        const val STATE_SEARCHING_FOR_SPAWN: Int = 0
        const val STATE_READY_TO_SPAWN: Int = 1
        const val STATE_CLIENT_READY_TO_SPAWN: Int = 2
    }
}
