package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.AbilityType
import org.chorus_oss.protocol.types.PlayerAbility


class RequestAbilityPacket : Packet(id) {
    var ability: PlayerAbility? = null
    var type: AbilityType? = null
    var boolValue: Boolean = false
    var floatValue: Float = 0f

    override fun pid(): Int {
        return ProtocolInfo.REQUEST_ABILITY_PACKET
    }



    companion object : PacketCodec<RequestAbilityPacket> {
        override fun deserialize(stream: Source): RequestAbilityPacket {
            val packet = RequestAbilityPacket()

            packet.ability = ABILITIES[byteBuf.readVarInt()]
            packet.type = ABILITY_TYPES[Proto.Byte.deserialize(stream).toInt()]
            packet.boolValue = Proto.Boolean.deserialize(stream)
            packet.floatValue = byteBuf.readFloatLE()

            return packet
        }

        val ABILITIES: Array<PlayerAbility> = UpdateAbilitiesPacket.VALID_FLAGS
        val ABILITY_TYPES: Array<AbilityType> = AbilityType.entries.toTypedArray()
    }
}
