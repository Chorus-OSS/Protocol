package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.AbilityLayer
import org.chorus_oss.protocol.types.CommandPermission
import org.chorus_oss.protocol.types.PlayerAbility
import org.chorus_oss.protocol.types.PlayerPermission
import java.util.*


class UpdateAbilitiesPacket : DataPacket() {
    @JvmField
    var entityId: Long = 0

    @JvmField
    var playerPermission: PlayerPermission? = null

    @JvmField
    var commandPermission: CommandPermission? = null

    @JvmField
    val abilityLayers: MutableList<AbilityLayer> = mutableListOf()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeLongLE(this.entityId)
        byteBuf.writeUnsignedVarInt(playerPermission!!.ordinal)
        byteBuf.writeUnsignedVarInt(commandPermission!!.ordinal)
        byteBuf.writeArray(
            this.abilityLayers
        ) { byteBuf: ByteBuf, abilityLayer: AbilityLayer ->
            writeAbilityLayer(
                byteBuf,
                abilityLayer
            )
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_ABILITIES_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object {
        val VALID_FLAGS: Array<PlayerAbility> = arrayOf(
            PlayerAbility.BUILD,
            PlayerAbility.MINE,
            PlayerAbility.DOORS_AND_SWITCHES,
            PlayerAbility.OPEN_CONTAINERS,
            PlayerAbility.ATTACK_PLAYERS,
            PlayerAbility.ATTACK_MOBS,
            PlayerAbility.OPERATOR_COMMANDS,
            PlayerAbility.TELEPORT,
            PlayerAbility.INVULNERABLE,
            PlayerAbility.FLYING,
            PlayerAbility.MAY_FLY,
            PlayerAbility.INSTABUILD,
            PlayerAbility.LIGHTNING,
            PlayerAbility.FLY_SPEED,
            PlayerAbility.WALK_SPEED,
            PlayerAbility.MUTED,
            PlayerAbility.WORLD_BUILDER,
            PlayerAbility.NO_CLIP,
            PlayerAbility.PRIVILEGED_BUILDER,
            PlayerAbility.VERTICAL_FLY_SPEED
        )
        val FLAGS_TO_BITS: EnumMap<PlayerAbility, Int> = EnumMap(
            PlayerAbility::class.java
        )

        init {
            for (i in VALID_FLAGS.indices) {
                FLAGS_TO_BITS[VALID_FLAGS[i]] =
                    (1 shl i)
            }
        }

        private fun getAbilitiesNumber(abilities: Set<PlayerAbility>): Int {
            var number = 0
            for (ability in abilities) {
                number = number or FLAGS_TO_BITS.getOrDefault(ability, 0)
            }
            return number
        }

        fun writeAbilityLayer(byteBuf: ByteBuf, abilityLayer: AbilityLayer) {
            byteBuf.writeShortLE(abilityLayer.layerType.ordinal)
            byteBuf.writeIntLE(getAbilitiesNumber(abilityLayer.abilitiesSet))
            byteBuf.writeIntLE(getAbilitiesNumber(abilityLayer.abilityValues))
            byteBuf.writeFloatLE(abilityLayer.flySpeed)
            byteBuf.writeFloatLE(abilityLayer.verticalFlySpeed)
            byteBuf.writeFloatLE(abilityLayer.walkSpeed)
        }
    }
}
