package org.chorus_oss.protocol.packets

import org.chorus_oss.protocol.types.Vector3f

import java.util.*


class SpawnParticleEffectPacket : Packet(id) {
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


}
