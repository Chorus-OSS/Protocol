package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.inventory.FullContainerName

data class ContainerRegistryCleanupPacket(
    val removedContainers: List<FullContainerName>
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeArray(this.removedContainers) { fullContainerName ->
            byteBuf.writeFullContainerName(fullContainerName)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.CONTAINER_REGISTRY_CLEANUP_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
