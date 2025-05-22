package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.Vector3f

data class UpdateClientInputLocksPacket(
    val locks: UInt,
    val position: Vector3f
) : Packet(id) {
    companion object : PacketCodec<UpdateClientInputLocksPacket> {
        init { PacketRegistry.register(this) }

        const val FLAG_LOCK_CAMERA: UInt = 0x1u
        const val FLAG_LOCK_MOVEMENT: UInt = 0x2u

        override val id: Int
            get() = ProtocolInfo.UPDATE_CLIENT_INPUT_LOCKS_PACKET

        override fun serialize(
            value: UpdateClientInputLocksPacket,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.locks, stream)
            Vector3f.serialize(value.position, stream)
        }

        override fun deserialize(stream: Source): UpdateClientInputLocksPacket {
            return UpdateClientInputLocksPacket(
                locks = ProtoVAR.UInt.deserialize(stream),
                position = Vector3f.deserialize(stream),
            )
        }
    }
}
