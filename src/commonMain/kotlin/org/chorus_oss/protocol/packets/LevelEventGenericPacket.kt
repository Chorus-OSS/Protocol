package org.chorus_oss.protocol.packets

import io.netty.handler.codec.EncoderException
import org.chorus_oss.chorus.nbt.tag.CompoundTag

import java.io.IOException
import java.nio.ByteOrder


class LevelEventGenericPacket : DataPacket() {
    var eventId: Int = 0
    var tag: CompoundTag? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(eventId)
        try {
            byteBuf.writeBytes(writeValue(tag!!, ByteOrder.LITTLE_ENDIAN, true))
        } catch (e: IOException) {
            throw EncoderException(e)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.LEVEL_EVENT_GENERIC_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
