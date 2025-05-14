package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.Player
import org.chorus_oss.chorus.Server

import org.chorus_oss.protocol.types.PlayerAbility
import org.chorus_oss.protocol.types.PlayerPermission


class RequestPermissionsPacket : Packet(id) {
    var uniqueEntityId: Long = 0
    var permissions: PlayerPermission? = null
    var customPermissions: Int = 0

    fun parseCustomPermissions(): Set<PlayerAbility> {
        val abilities = HashSet<PlayerAbility>()
        for (controllableAbility in CONTROLLABLE_ABILITIES) {
            if ((this.customPermissions and controllableAbility.bit) != 0) abilities.add(controllableAbility)
        }
        return abilities
    }

    val targetPlayer: Player?
        get() {
            for (player in Server.instance.onlinePlayers.values) {
                if (player.getUniqueID() == this.uniqueEntityId) return player
            }
            return null
        }

    override fun pid(): Int {
        return ProtocolInfo.REQUEST_PERMISSIONS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<RequestPermissionsPacket> {
        override fun decode(byteBuf: ByteBuf): RequestPermissionsPacket {
            val packet = RequestPermissionsPacket()

            packet.uniqueEntityId = byteBuf.readLongLE()
            packet.permissions = PlayerPermission.entries[byteBuf.readByte() / 2]
            packet.customPermissions = byteBuf.readShortLE().toInt()

            return packet
        }

        // Controllable capabilities in the permission list
        @JvmField
        val CONTROLLABLE_ABILITIES: Array<PlayerAbility> = arrayOf(
            PlayerAbility.BUILD,
            PlayerAbility.MINE,
            PlayerAbility.DOORS_AND_SWITCHES,
            PlayerAbility.OPEN_CONTAINERS,
            PlayerAbility.ATTACK_PLAYERS,
            PlayerAbility.ATTACK_MOBS,
            PlayerAbility.OPERATOR_COMMANDS,
            PlayerAbility.TELEPORT
        )
    }
}
