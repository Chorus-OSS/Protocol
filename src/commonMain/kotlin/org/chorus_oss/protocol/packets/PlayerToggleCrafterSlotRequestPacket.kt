package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int

data class PlayerToggleCrafterSlotRequestPacket(
    val posX: Int,
    val posY: Int,
    val posZ: Int,
    val slot: Byte,
    val disabled: Boolean,
) : Packet(id) {
    companion object : PacketCodec<PlayerToggleCrafterSlotRequestPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.PLAYER_TOGGLE_CRAFTER_SLOT_REQUEST_PACKET

        override fun serialize(
            value: PlayerToggleCrafterSlotRequestPacket,
            stream: Sink
        ) {
            ProtoLE.Int.serialize(value.posX, stream)
            ProtoLE.Int.serialize(value.posY, stream)
            ProtoLE.Int.serialize(value.posZ, stream)
            Proto.Byte.serialize(value.slot, stream)
            Proto.Boolean.serialize(value.disabled, stream)
        }

        override fun deserialize(stream: Source): PlayerToggleCrafterSlotRequestPacket {
            return PlayerToggleCrafterSlotRequestPacket(
                posX = ProtoLE.Int.deserialize(stream),
                posY = ProtoLE.Int.deserialize(stream),
                posZ = ProtoLE.Int.deserialize(stream),
                slot = Proto.Byte.deserialize(stream),
                disabled = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
