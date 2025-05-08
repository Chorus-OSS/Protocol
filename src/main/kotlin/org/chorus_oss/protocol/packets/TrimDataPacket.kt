package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.TrimMaterial
import org.chorus_oss.protocol.types.TrimPattern

class TrimDataPacket : DataPacket() {
    val patterns: MutableList<TrimPattern> = mutableListOf()
    val materials: MutableList<TrimMaterial> = mutableListOf()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(patterns.size)
        patterns.forEach { p: TrimPattern ->
            byteBuf.writeString(p.itemName)
            byteBuf.writeString(p.patternId)
        }
        byteBuf.writeUnsignedVarInt(materials.size)
        materials.forEach { m: TrimMaterial ->
            byteBuf.writeString(m.materialId)
            byteBuf.writeString(m.color)
            byteBuf.writeString(m.itemName)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.TRIM_DATA
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<TrimDataPacket> {
        override fun decode(byteBuf: ByteBuf): TrimDataPacket {
            val packet = TrimDataPacket()

            val length1 = byteBuf.readUnsignedVarInt()
            for (i in 0..<length1) {
                packet.patterns.add(TrimPattern(byteBuf.readString(), byteBuf.readString()))
            }
            val length2 = byteBuf.readUnsignedVarInt()
            for (i in 0..<length2) {
                packet.materials.add(TrimMaterial(byteBuf.readString(), byteBuf.readString(), byteBuf.readString()))
            }

            return packet
        }
    }
}
