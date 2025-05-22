package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3

data class OpenSignPacket(
    val position: IVector3,
    val frontSide: Boolean,
) : Packet(id) {
    companion object : PacketCodec<OpenSignPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.OPEN_SIGN_PACKET

        override fun serialize(value: OpenSignPacket, stream: Sink) {
            UIVector3.serialize(value.position, stream)
            Proto.Boolean.serialize(value.frontSide, stream)
        }

        override fun deserialize(stream: Source): OpenSignPacket {
            return OpenSignPacket(
                position = UIVector3.deserialize(stream),
                frontSide = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
