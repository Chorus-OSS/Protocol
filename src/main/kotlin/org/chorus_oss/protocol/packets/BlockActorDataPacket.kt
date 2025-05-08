package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3
import org.chorus_oss.chorus.nbt.NBTIO
import org.chorus_oss.chorus.nbt.tag.CompoundTag

import java.nio.ByteOrder

data class BlockActorDataPacket(
    var blockPosition: BlockVector3,
    var actorDataTags: CompoundTag
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBlockVector3(this.blockPosition)
        byteBuf.writeBytes(NBTIO.write(actorDataTags, ByteOrder.LITTLE_ENDIAN, true))
    }

    override fun pid(): Int {
        return ProtocolInfo.BLOCK_ACTOR_DATA_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
