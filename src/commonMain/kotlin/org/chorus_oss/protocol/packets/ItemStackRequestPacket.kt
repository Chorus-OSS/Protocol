package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequest


class ItemStackRequestPacket : Packet(id) {
    val requests: MutableList<ItemStackRequest> = ArrayList()

    override fun pid(): Int {
        return ProtocolInfo.ITEM_STACK_REQUEST_PACKET
    }



    companion object : PacketCodec<ItemStackRequestPacket> {
        override fun deserialize(stream: Source): ItemStackRequestPacket {
            val packet = ItemStackRequestPacket()

            packet.requests.addAll(
                byteBuf.readArray(
                    ItemStackRequest::class.java
                ) { it.readItemStackRequest() }
            )

            return packet
        }
    }
}
