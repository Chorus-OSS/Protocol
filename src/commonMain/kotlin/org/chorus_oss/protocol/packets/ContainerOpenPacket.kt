package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.ContainerType
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3

data class ContainerOpenPacket(
    val containerID: Byte,
    val containerType: ContainerType,
    val position: IVector3,
    val targetActorID: ActorUniqueID,
) : Packet(id) {
    companion object : PacketCodec<ContainerOpenPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.CONTAINER_OPEN_PACKET

        override fun serialize(value: ContainerOpenPacket, stream: Sink) {
            Proto.Byte.serialize(value.containerID, stream)
            ContainerType.serialize(value.containerType, stream)
            UIVector3.serialize(value.position, stream)
            ActorUniqueID.serialize(value.targetActorID, stream)
        }

        override fun deserialize(stream: Source): ContainerOpenPacket {
            return ContainerOpenPacket(
                containerID = Proto.Byte.deserialize(stream),
                containerType = ContainerType.deserialize(stream),
                position = UIVector3.deserialize(stream),
                targetActorID = ActorUniqueID.deserialize(stream)
            )
        }
    }
}
