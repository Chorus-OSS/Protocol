package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.ContainerType
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos

data class ContainerOpenPacket(
    val containerID: Byte,
    val containerType: ContainerType,
    val position: BlockPos,
    val targetActorID: Long,
) : Packet(id) {
    companion object : PacketCodec<ContainerOpenPacket> {
        override val id: Int = 46

        override fun serialize(value: ContainerOpenPacket, stream: Sink) {
            Proto.Byte.serialize(value.containerID, stream)
            ContainerType.serialize(value.containerType, stream)
            NetBlockPos.serialize(value.position, stream)
            ActorUniqueID.serialize(value.targetActorID, stream)
        }

        override fun deserialize(stream: Source): ContainerOpenPacket {
            return ContainerOpenPacket(
                containerID = Proto.Byte.deserialize(stream),
                containerType = ContainerType.deserialize(stream),
                position = NetBlockPos.deserialize(stream),
                targetActorID = ActorUniqueID.deserialize(stream)
            )
        }
    }
}
