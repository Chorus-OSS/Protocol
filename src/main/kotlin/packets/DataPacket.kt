package org.chorus_oss.protocol.packets



abstract class DataPacket {
    open fun decode(byteBuf: ByteBuf) {}

    open fun encode(byteBuf: ByteBuf) {}

    abstract fun pid(): Int

    abstract fun handle(handler: PacketHandler)
}

interface PacketDecoder<T : DataPacket> {
    fun decode(byteBuf: ByteBuf): T
}

interface PacketEncoder {
    fun encode(byteBuf: ByteBuf)
}
