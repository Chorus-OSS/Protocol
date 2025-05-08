package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.ActorUniqueID

data class ChangeMobPropertyPacket(
    val actorID: ActorUniqueID,
    val propertyName: String,
    val boolComponentValue: Boolean,
    val stringComponentValue: String,
    val intComponentValue: Int,
    val floatComponentValue: Float,
) : DataPacket() {
    override fun pid(): Int {
        return ProtocolInfo.CHANGE_MOB_PROPERTY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ChangeMobPropertyPacket> {
        override fun decode(byteBuf: ByteBuf): ChangeMobPropertyPacket {
            return ChangeMobPropertyPacket(
                actorID = byteBuf.readActorUniqueID(),
                propertyName = byteBuf.readString(),
                boolComponentValue = byteBuf.readBoolean(),
                stringComponentValue = byteBuf.readString(),
                intComponentValue = byteBuf.readInt(),
                floatComponentValue = byteBuf.readFloatLE(),
            )
        }
    }
}
