package org.chorus_oss.protocol.packets


class UnlockedRecipesPacket : Packet(id) {
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



    companion object : PacketCodec<UnlockedRecipesPacket> {
        override fun deserialize(stream: Source): UnlockedRecipesPacket {
            val packet = UnlockedRecipesPacket()

            packet.unlockedNotification = Proto.Boolean.deserialize(stream)
            for (i in 0..<byteBuf.readUnsignedVarInt()) {
                packet.unlockedRecipes.add(Proto.String.deserialize(stream))
            }

            return packet
        }
    }
}
