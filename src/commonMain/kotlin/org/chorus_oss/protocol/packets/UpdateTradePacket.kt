package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.nbt.tag.CompoundTag

import org.chorus_oss.chorus.utils.Loggable


import java.io.IOException
import java.nio.ByteOrder


class UpdateTradePacket : DataPacket() {
    var containerId: Byte = 0
    var containerType: Byte = 15 //trading id
    var size: Int = 0 // hardcoded to 0
    var tradeTier: Int = 0 //交易等级
    var traderUniqueEntityId: Long = 0 //村民id
    var playerUniqueEntityId: Long = 0 //村民id
    var displayName: String? = null //硬编码的显示名
    var offers: CompoundTag? = null //交易配方
    var newTradingUi: Boolean = false //是否启用新版交易ui
    var usingEconomyTrade: Boolean = false //未知

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(containerId.toInt())
        byteBuf.writeByte(containerType.toInt())
        byteBuf.writeVarInt(size)
        byteBuf.writeVarInt(tradeTier)
        byteBuf.writeActorUniqueID(traderUniqueEntityId)
        byteBuf.writeActorUniqueID(playerUniqueEntityId)
        byteBuf.writeString(displayName!!)
        byteBuf.writeBoolean(newTradingUi)
        byteBuf.writeBoolean(usingEconomyTrade)
        try {
            byteBuf.writeBytes(write(offers!!, ByteOrder.LITTLE_ENDIAN, true))
        } catch (e: IOException) {
            UpdateTradePacket.log.error("", e)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_TRADE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : Loggable
}
