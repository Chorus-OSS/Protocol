package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.entity.data.EntityFlag
import org.chorus_oss.chorus.math.Vector3f

import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorUniqueID

data class ClientMovementPredictionSyncPacket(
    val flags: MutableSet<EntityFlag>,
    val actorBoundingBox: Vector3f,
    val movementAttributesComponent: MovementAttributesComponent,
    val actorUniqueID: ActorUniqueID,
    val actorFlyingState: Boolean,
) : DataPacket() {
    data class MovementAttributesComponent(
        val movementSpeed: Float,
        val underwaterMovementSpeed: Float,
        val lavaMovementSpeed: Float,
        val jumpStrength: Float,
        val health: Float,
        val hunger: Float
    )

    override fun pid(): Int {
        return ProtocolInfo.CLIENT_MOVEMENT_PREDICTION_SYNC_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ClientMovementPredictionSyncPacket> {
        override fun decode(byteBuf: ByteBuf): ClientMovementPredictionSyncPacket {
            return ClientMovementPredictionSyncPacket(
                flags = run {
                    val flagBits = byteBuf.readUnsignedBigVarInt(EntityFlag.entries.size)
                    EntityFlag.entries.filter {
                        flagBits.testBit(it.ordinal)
                    }.toMutableSet()
                },
                actorBoundingBox = byteBuf.readVector3f(),
                movementAttributesComponent = readMovementAttributesComponent(byteBuf),
                actorUniqueID = byteBuf.readActorUniqueID(),
                actorFlyingState = byteBuf.readBoolean(),
            )
        }

        private fun readMovementAttributesComponent(byteBuf: ByteBuf): MovementAttributesComponent {
            return MovementAttributesComponent(
                movementSpeed = byteBuf.readFloatLE(),
                underwaterMovementSpeed = byteBuf.readFloatLE(),
                lavaMovementSpeed = byteBuf.readFloatLE(),
                jumpStrength = byteBuf.readFloatLE(),
                health = byteBuf.readFloatLE(),
                hunger = byteBuf.readFloatLE()
            )
        }
    }
}
