package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.inventory.InventoryType

data class ContainerClosePacket(
    val containerID: Int,
    val containerType: InventoryType,
    val serverInitiatedClose: Boolean,
) : Packet(id) {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(containerID)
        byteBuf.writeByte(containerType.networkType)
        byteBuf.writeBoolean(this.serverInitiatedClose)
    }

    override fun pid(): Int {
        return ProtocolInfo.CONTAINER_CLOSE_PACKET
    }



    companion object : PacketCodec<ContainerClosePacket> {
        override fun deserialize(stream: Source): ContainerClosePacket {
            return ContainerClosePacket(
                containerID = Proto.Byte.deserialize(stream).toInt(),
                containerType = InventoryType.from(Proto.Byte.deserialize(stream).toInt()),
                serverInitiatedClose = Proto.Boolean.deserialize(stream)
            )
        }
    }
}
