package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3

import org.chorus_oss.protocol.ProtocolInfo

data class AnvilDamagePacket(
    val damageAmount: Byte,
    val blockPosition: BlockVector3,
) : DataPacket() {
    override fun pid(): Int {
        return ProtocolInfo.ANVIL_DAMAGE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<AnvilDamagePacket> {
        override fun decode(byteBuf: ByteBuf): AnvilDamagePacket {
            return AnvilDamagePacket(
                damageAmount = byteBuf.readByte(),
                blockPosition = byteBuf.readBlockVector3()
            )
        }
    }
}
