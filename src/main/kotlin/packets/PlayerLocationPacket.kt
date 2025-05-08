package org.chorus_oss.chorus.network.protocol

import org.chorus_oss.chorus.math.Vector3f
import org.chorus_oss.chorus.network.connection.util.HandleByteBuf
import org.chorus_oss.chorus.network.protocol.types.ActorRuntimeID

data class PlayerLocationPacket(
    val type: Type,
    val targetActorID: ActorRuntimeID,
    val position: Vector3f? = null,
) : DataPacket(), PacketEncoder {
    override fun pid(): Int {
        return ProtocolInfo.PLAYER_LOCATION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    override fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeIntLE(this.type.ordinal)
        byteBuf.writeActorRuntimeID(this.targetActorID)
        if (this.type == Type.COORDINATES) {
            byteBuf.writeVector3f(this.position!!)
        }
    }

    enum class Type {
        COORDINATES,
        HIDE,
    }

    companion object : PacketDecoder<PlayerLocationPacket> {
        override fun decode(byteBuf: HandleByteBuf): PlayerLocationPacket {
            val type: Type
            return PlayerLocationPacket(
                type = Type.entries[byteBuf.readByte().toInt()].also { type = it },
                targetActorID = byteBuf.readActorRuntimeID(),
                position = if (type == Type.COORDINATES) byteBuf.readVector3f() else null
            )
        }
    }
}