package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class UpdateSoftEnumPacket : DataPacket() {
    var values: List<String> = listOf()
    var name: String = ""
    var type: Type = Type.SET

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(name)
        byteBuf.writeUnsignedVarInt(values.size)

        for (value in values) {
            byteBuf.writeString(value)
        }
        byteBuf.writeByte(type.ordinal.toByte().toInt())
    }

    enum class Type {
        ADD,
        REMOVE,
        SET
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_SOFT_ENUM_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
