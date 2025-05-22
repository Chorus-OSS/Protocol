package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ContainerType

data class ContainerClosePacket(
    val containerID: Byte,
    val containerType: ContainerType,
    val serverInitiatedClose: Boolean,
) : Packet(id) {
    companion object : PacketCodec<ContainerClosePacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.CONTAINER_CLOSE_PACKET

        override fun deserialize(stream: Source): ContainerClosePacket {
            return ContainerClosePacket(
                containerID = Proto.Byte.deserialize(stream),
                containerType = ContainerType.deserialize(stream),
                serverInitiatedClose = Proto.Boolean.deserialize(stream)
            )
        }

        override fun serialize(value: ContainerClosePacket, stream: Sink) {
            Proto.Byte.serialize(value.containerID, stream)
            ContainerType.serialize(value.containerType, stream)
            Proto.Boolean.serialize(value.serverInitiatedClose, stream)
        }
    }
}
