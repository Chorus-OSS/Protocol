package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.nbt.NBTIO.write
import org.chorus_oss.chorus.nbt.tag.CompoundTag

import org.chorus_oss.chorus.utils.MainLogger
import org.chorus_oss.protocol.ProtocolInfo

import java.io.IOException
import java.nio.ByteOrder

class ItemRegistryPacket : DataPacket() {
    var entries: Array<Entry>? = emptyArray()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(entries!!.size)
        try {
            for (entry in entries!!) {
                byteBuf.writeString(entry.name)
                byteBuf.writeShortLE(entry.runtimeId)
                byteBuf.writeBoolean(entry.componentBased)
                byteBuf.writeVarInt(entry.version)
                byteBuf.writeBytes(write(entry.data, ByteOrder.LITTLE_ENDIAN, true))
            }
        } catch (e: IOException) {
            MainLogger.logger.error("Error while encoding NBT data of ItemRegistryPacket", e)
        }
    }

    class Entry(
        val name: String,
        val runtimeId: Int,
        val version: Int,
        val componentBased: Boolean,
        val data: CompoundTag
    )

    override fun pid(): Int {
        return ProtocolInfo.ITEM_REGISTRY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
