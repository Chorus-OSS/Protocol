package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.AbilityType
import org.chorus_oss.protocol.types.PlayerAbility


class RequestAbilityPacket : DataPacket() {
    var ability: PlayerAbility? = null
    var type: AbilityType? = null
    var boolValue: Boolean = false
    var floatValue: Float = 0f

    override fun pid(): Int {
        return ProtocolInfo.REQUEST_ABILITY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<RequestAbilityPacket> {
        override fun decode(byteBuf: ByteBuf): RequestAbilityPacket {
            val packet = RequestAbilityPacket()

            packet.ability = ABILITIES[byteBuf.readVarInt()]
            packet.type = ABILITY_TYPES[byteBuf.readByte().toInt()]
            packet.boolValue = byteBuf.readBoolean()
            packet.floatValue = byteBuf.readFloatLE()

            return packet
        }

        val ABILITIES: Array<PlayerAbility> = UpdateAbilitiesPacket.VALID_FLAGS
        val ABILITY_TYPES: Array<AbilityType> = AbilityType.entries.toTypedArray()
    }
}
