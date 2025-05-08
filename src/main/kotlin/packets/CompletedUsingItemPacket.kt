package org.chorus_oss.protocol.packets


data class CompletedUsingItemPacket(
    val itemID: Short,
    val itemUseMethod: ItemUseMethod,
) : DataPacket(), PacketEncoder {
    enum class ItemUseMethod(val id: Int) {
        UNKNOWN(-1),
        EQUIP_ARMOR(0),
        EAT(1),
        ATTACK(2),
        CONSUME(3),
        THROW(4),
        SHOOT(5),
        PLACE(6),
        FILL_BOTTLE(7),
        FILL_BUCKET(8),
        POUR_BUCKET(9),
        USE_TOOL(10),
        INTERACT(11),
        RETRIEVED(12),
        DYED(13),
        TRADED(14),
        BRUSHING_COMPLETED(15),
        OPENED_VAULT(16)
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeShortLE(this.itemID.toInt())
        byteBuf.writeIntLE(this.itemUseMethod.id)
    }

    override fun pid(): Int {
        return ProtocolInfo.COMPLETED_USING_ITEM_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object {
        const val ACTION_EAT: Int = 1
    }
}
