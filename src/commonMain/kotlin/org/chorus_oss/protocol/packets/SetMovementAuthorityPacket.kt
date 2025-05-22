package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.PlayerMovementMode

data class SetMovementAuthorityPacket(
    val movementType: PlayerMovementMode,
) : Packet(id) {
    companion object : PacketCodec<SetMovementAuthorityPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.SET_MOVEMENT_AUTHORITY_PACKET

        override fun serialize(
            value: SetMovementAuthorityPacket,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.movementType.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): SetMovementAuthorityPacket {
            return SetMovementAuthorityPacket(
                movementType = PlayerMovementMode.entries[Proto.Byte.deserialize(stream).toInt()]
            )
        }
    }
}
