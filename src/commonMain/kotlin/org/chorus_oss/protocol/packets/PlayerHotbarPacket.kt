package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.UInt

data class PlayerHotbarPacket(
    val selectedHotbarSlot: UInt,
    val windowID: Byte,
    val selectHotbarSlot: Boolean
) : Packet(id) {
    companion object : PacketCodec<PlayerHotbarPacket> {
        override val id: Int = 48

        override fun serialize(value: PlayerHotbarPacket, stream: Sink) {
            ProtoVAR.UInt.serialize(value.selectedHotbarSlot, stream)
            Proto.Byte.serialize(value.windowID, stream)
            Proto.Boolean.serialize(value.selectHotbarSlot, stream)
        }

        override fun deserialize(stream: Source): PlayerHotbarPacket {
            return PlayerHotbarPacket(
                selectedHotbarSlot = ProtoVAR.UInt.deserialize(stream),
                windowID = Proto.Byte.deserialize(stream),
                selectHotbarSlot = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
