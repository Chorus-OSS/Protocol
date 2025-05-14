package org.chorus_oss.protocol.packets


import io.netty.buffer.ByteBufInputStream
import org.chorus_oss.chorus.nbt.tag.CompoundTag

import org.chorus_oss.chorus.utils.Loggable
import java.nio.ByteOrder


class SyncEntityPropertyPacket(
    var data: CompoundTag? = null
) : Packet(id) {
    override fun encode(byteBuf: ByteBuf) {
        try {
            byteBuf.writeBytes(write(data!!, ByteOrder.BIG_ENDIAN, true))
        } catch (e: Exception) {
            SyncEntityPropertyPacket.log.error("", e)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.SYNC_ENTITY_PROPERTY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SyncEntityPropertyPacket>, Loggable {
        override fun decode(byteBuf: ByteBuf): SyncEntityPropertyPacket {
            val packet = SyncEntityPropertyPacket()

            try {
                ByteBufInputStream(byteBuf).use { stream ->
                    packet.data = read(stream, ByteOrder.BIG_ENDIAN, true)
                }
            } catch (e: Exception) {
                SyncEntityPropertyPacket.log.error("", e)
            }

            return packet
        }
    }
}
