package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorUniqueID

data class ActorPickRequestPacket(
    val actorID: ActorUniqueID,
    val maxSlots: Byte,
    val withData: Boolean,
) : DataPacket() {
    override fun pid(): Int {
        return ProtocolInfo.ACTOR_PICK_REQUEST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ActorPickRequestPacket> {
        override fun decode(byteBuf: ByteBuf): ActorPickRequestPacket {
            return ActorPickRequestPacket(
                actorID = byteBuf.readActorUniqueID(),
                maxSlots = byteBuf.readByte(),
                withData = byteBuf.readBoolean(),
            )
        }
    }
}
