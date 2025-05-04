package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.Vector3f

import org.chorus_oss.protocol.ProtocolInfo

import java.util.*


class SpawnParticleEffectPacket : DataPacket() {
    var dimensionId: Int = 0
    var uniqueEntityId: Long = -1
    var position: Vector3f? = null
    var identifier: String? = null
    var molangVariablesJson: Optional<String> = Optional.empty()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(dimensionId.toByte().toInt())
        byteBuf.writeActorUniqueID(uniqueEntityId)
        byteBuf.writeVector3f(position!!)
        byteBuf.writeString(identifier!!)
        byteBuf.writeBoolean(molangVariablesJson.isPresent)
        molangVariablesJson.ifPresent { str: String? ->
            byteBuf.writeString(
                str!!
            )
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.SPAWN_PARTICLE_EFFECT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
