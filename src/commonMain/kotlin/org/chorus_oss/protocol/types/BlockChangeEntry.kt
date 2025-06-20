package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt

data class BlockChangeEntry(
    val blockPos: BlockPos,
    val blockRuntimeID: UInt,
    val flags: UInt,
    val syncedUpdateEntityUniqueID: Long,
    val syncedUpdateType: MessageType
) {
    companion object : ProtoCodec<BlockChangeEntry> {
        enum class MessageType {
            None,
            Create,
            Destroy;

            companion object : ProtoCodec<MessageType> {
                override fun serialize(
                    value: MessageType,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): MessageType {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: BlockChangeEntry, stream: Sink) {
            NetBlockPos.serialize(value.blockPos, stream)
            ProtoVAR.UInt.serialize(value.blockRuntimeID, stream)
            ProtoVAR.UInt.serialize(value.flags, stream)
            ActorUniqueID.serialize(value.syncedUpdateEntityUniqueID, stream)
            MessageType.serialize(value.syncedUpdateType, stream)
        }

        override fun deserialize(stream: Source): BlockChangeEntry {
            return BlockChangeEntry(
                blockPos = NetBlockPos.deserialize(stream),
                blockRuntimeID = ProtoVAR.UInt.deserialize(stream),
                flags = ProtoVAR.UInt.deserialize(stream),
                syncedUpdateEntityUniqueID = ActorUniqueID.deserialize(stream),
                syncedUpdateType = MessageType.deserialize(stream),
            )
        }
    }
}
