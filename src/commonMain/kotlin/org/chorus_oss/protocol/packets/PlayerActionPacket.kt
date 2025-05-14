package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3


class PlayerActionPacket : Packet(id) {
    @JvmField
    var entityId: Long = 0

    @JvmField
    var action: Int = 0
    var x: Int = 0
    var y: Int = 0
    var z: Int = 0
    var resultPosition: BlockVector3? = null
    var face: Int = 0

    override fun decode(byteBuf: ByteBuf) {

    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.entityId)
        byteBuf.writeVarInt(this.action)
        byteBuf.writeBlockVector3(this.x, this.y, this.z)
        byteBuf.writeBlockVector3((if (this.resultPosition != null) this.resultPosition else BlockVector3())!!)
        byteBuf.writeVarInt(this.face)
    }

    override fun pid(): Int {
        return ProtocolInfo.PLAYER_ACTION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PlayerActionPacket> {
        override fun decode(byteBuf: ByteBuf): PlayerActionPacket {
            val packet = PlayerActionPacket()

            packet.entityId = byteBuf.readActorRuntimeID()
            packet.action = byteBuf.readVarInt()
            val v = byteBuf.readBlockVector3()
            packet.x = v.x
            packet.y = v.y
            packet.z = v.z
            packet.resultPosition = byteBuf.readBlockVector3()
            packet.face = byteBuf.readVarInt()

            return packet
        }

        const val ACTION_START_BREAK: Int = 0
        const val ACTION_ABORT_BREAK: Int = 1
        const val ACTION_STOP_BREAK: Int = 2
        const val ACTION_GET_UPDATED_BLOCK: Int = 3
        const val ACTION_DROP_ITEM: Int = 4
        const val ACTION_START_SLEEPING: Int = 5
        const val ACTION_STOP_SLEEPING: Int = 6
        const val ACTION_RESPAWN: Int = 7
        const val ACTION_JUMP: Int = 8
        const val ACTION_START_SPRINT: Int = 9
        const val ACTION_STOP_SPRINT: Int = 10
        const val ACTION_START_SNEAK: Int = 11
        const val ACTION_STOP_SNEAK: Int = 12
        const val ACTION_CREATIVE_PLAYER_DESTROY_BLOCK: Int = 13
        const val ACTION_DIMENSION_CHANGE_ACK: Int =
            14 // sent when spawning in a different dimension to tell the server we spawned
        const val ACTION_START_GLIDE: Int = 15
        const val ACTION_STOP_GLIDE: Int = 16
        const val ACTION_BUILD_DENIED: Int = 17
        const val ACTION_CONTINUE_BREAK: Int = 18
        const val ACTION_SET_ENCHANTMENT_SEED: Int = 20
        const val ACTION_START_SWIMMING: Int = 21
        const val ACTION_STOP_SWIMMING: Int = 22
        const val ACTION_START_SPIN_ATTACK: Int = 23
        const val ACTION_STOP_SPIN_ATTACK: Int = 24
        const val ACTION_INTERACT_BLOCK: Int = 25
        const val ACTION_PREDICT_DESTROY_BLOCK: Int = 26
        const val ACTION_CONTINUE_DESTROY_BLOCK: Int = 27
        const val ACTION_START_ITEM_USE_ON: Int = 28
        const val ACTION_STOP_ITEM_USE_ON: Int = 29
        const val ACTION_START_FLYING: Int = 34
        const val ACTION_STOP_FLYING: Int = 35
        const val ACTION_RECEIVED_SERVER_DATA: Int = 36
    }
}
