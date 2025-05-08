package org.chorus_oss.protocol.types



data class MaterialReducerDataEntry(
    val fromItemKeyInput: Int,
    val itemIDsAndCounts: List<Item>
) {
    fun write(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(fromItemKeyInput)
        byteBuf.writeArray(itemIDsAndCounts) { buf, item ->
            buf.writeVarInt(item.itemID)
            buf.writeVarInt(item.itemCount)
        }
    }

    data class Item(
        val itemID: Int,
        val itemCount: Int,
    )
}