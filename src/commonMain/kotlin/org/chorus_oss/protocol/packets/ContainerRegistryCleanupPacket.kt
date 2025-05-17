package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.inventory.FullContainerName

data class ContainerRegistryCleanupPacket(
    val removedContainers: List<FullContainerName>
) : Packet(id) {
    companion object : PacketCodec<ContainerRegistryCleanupPacket> {
        override val id: Int
            get() = ProtocolInfo.CONTAINER_REGISTRY_CLEANUP_PACKET

        override fun serialize(
            value: ContainerRegistryCleanupPacket,
            stream: Sink
        ) {
            ProtoHelper.serializeList(value.removedContainers, stream, FullContainerName)
        }

        override fun deserialize(stream: Source): ContainerRegistryCleanupPacket {
            return ContainerRegistryCleanupPacket(
                removedContainers = ProtoHelper.deserializeList(stream, FullContainerName)
            )
        }
    }
}
