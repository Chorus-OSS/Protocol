package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class UnlockedRecipesPacket : DataPacket() {
    var unlockedNotification: Boolean = false
    val unlockedRecipes: MutableList<String> = mutableListOf()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.unlockedNotification)
        byteBuf.writeUnsignedVarInt(unlockedRecipes.size)
        for (recipe in this.unlockedRecipes) {
            byteBuf.writeString(recipe)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.UNLOCKED_RECIPES_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<UnlockedRecipesPacket> {
        override fun decode(byteBuf: ByteBuf): UnlockedRecipesPacket {
            val packet = UnlockedRecipesPacket()

            packet.unlockedNotification = byteBuf.readBoolean()
            for (i in 0..<byteBuf.readUnsignedVarInt()) {
                packet.unlockedRecipes.add(byteBuf.readString())
            }

            return packet
        }
    }
}
