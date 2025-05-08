package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.ActorRuntimeID

class InteractPacket(
    val action: Action,
    val targetRuntimeID: ActorRuntimeID,
    val actionData: Action.ActionData?,
) : DataPacket(), PacketEncoder {

    enum class Action(val netOrdinal: Byte) {
        INVALID(0),
        STOP_RIDING(3),
        INTERACT_UPDATE(4),
        NPC_OPEN(5),
        OPEN_INVENTORY(6);

        interface ActionData

        data class PositionData(
            val positionX: Float,
            val positionY: Float,
            val positionZ: Float,
        ) : ActionData

        companion object {
            fun fromOrdinal(ordinal: Byte): Action {
                return entries.find { it.netOrdinal == ordinal }
                    ?: throw RuntimeException("Unknown InteractPacketAction ID: $ordinal")
            }
        }
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(action.netOrdinal.toInt())
        byteBuf.writeActorRuntimeID(this.targetRuntimeID)
    }

    override fun pid(): Int {
        return ProtocolInfo.INTERACT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<InteractPacket> {
        override fun decode(byteBuf: ByteBuf): InteractPacket {
            val action = Action.fromOrdinal(byteBuf.readByte())
            return InteractPacket(
                action = action,
                targetRuntimeID = byteBuf.readActorRuntimeID(),
                actionData = when (action) {
                    Action.INTERACT_UPDATE, Action.STOP_RIDING -> Action.PositionData(
                        positionX = byteBuf.readFloat(),
                        positionY = byteBuf.readFloat(),
                        positionZ = byteBuf.readFloat(),
                    )

                    else -> null
                }
            )
        }
    }
}
