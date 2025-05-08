package org.chorus_oss.protocol.packets


class ContainerSetDataPacket(
    val containerID: Byte,
    val property: Int,
    val value: Int,
) : DataPacket() {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(this.containerID.toInt())
        byteBuf.writeVarInt(this.property)
        byteBuf.writeVarInt(this.value)
    }

    override fun pid(): Int {
        return ProtocolInfo.CONTAINER_SET_DATA_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object {
        const val PROPERTY_FURNACE_TICK_COUNT: Int = 0
        const val PROPERTY_FURNACE_LIT_TIME: Int = 1
        const val PROPERTY_FURNACE_LIT_DURATION: Int = 2

        // TODO: check property 3
        const val PROPERTY_FURNACE_FUEL_AUX: Int = 4

        const val PROPERTY_BREWING_STAND_BREW_TIME: Int = 0
        const val PROPERTY_BREWING_STAND_FUEL_AMOUNT: Int = 1
        const val PROPERTY_BREWING_STAND_FUEL_TOTAL: Int = 2
    }
}
