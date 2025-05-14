package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.MovementEffectType


class MovementEffectPacket : Packet(id) {
    var targetRuntimeID: Long = 0
    lateinit var effectType: MovementEffectType
    var effectDuration: Int = 0
    var tick: Long = 0

    override fun deserialize(stream: Source) {
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarLong(this.targetRuntimeID)
        byteBuf.writeUnsignedVarInt(this.effectType.id)
        byteBuf.writeUnsignedVarInt(this.effectDuration)
        byteBuf.writeUnsignedVarLong(this.tick)
    }

    override fun pid(): Int {
        return ProtocolInfo.MOVEMENT_EFFECT_PACKET
    }


}
