package org.chorus_oss.protocol.types.item

import kotlinx.io.*
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.types.item.ItemInstance.Companion.ShieldID

data class ItemStack(
    val stackNetID: Int,
    val item: ItemInstance,
) {
    companion object : ProtoCodec<ItemStack> {
        override fun serialize(value: ItemStack, stream: Sink) {
            ProtoVAR.Int.serialize(value.item.netID, stream)
            if (value.item.netID == 0) {
                return // ItemStack is invalid, no more data.
            }

            ProtoLE.UShort.serialize(value.item.count, stream)
            ProtoVAR.UInt.serialize(value.item.metadata, stream)

            val hasNetID = value.stackNetID != 0
            Proto.Boolean.serialize(hasNetID, stream)
            if (hasNetID) {
                ProtoVAR.Int.serialize(value.stackNetID, stream)
            }

            ProtoVAR.Int.serialize(value.item.blockRuntimeID, stream)

            // region UserDataBuffer

            val userDataBuffer = Buffer()

            if (value.item.nbtData.isNotEmpty()) {
                ProtoLE.Short.serialize(-1, userDataBuffer) // Hardcoded
                Proto.UByte.serialize(1u, userDataBuffer) // Hardcoded
                Tag.serialize(value.item.nbtData, userDataBuffer, TagSerialization.LE, true)
            } else {
                ProtoLE.Short.serialize(0, userDataBuffer)
            }

            ProtoLE.UInt.serialize(value.item.canBePlacedOn.size.toUInt(), userDataBuffer)
            value.item.canBePlacedOn.forEach {
                Proto.String.serialize(it, userDataBuffer)
            }

            ProtoLE.UInt.serialize(value.item.canBreak.size.toUInt(), userDataBuffer)
            value.item.canBreak.forEach {
                Proto.String.serialize(it, userDataBuffer)
            }

            if (value.item.netID == ShieldID) {
                ProtoLE.Long.serialize(0, userDataBuffer)
            }

            // endregion

            Proto.ByteString.serialize(userDataBuffer.readByteString(), stream)
        }

        override fun deserialize(stream: Source): ItemStack {
            val netID = ProtoVAR.Int.deserialize(stream)
            if (netID == 0) {
                return ItemStack(
                    stackNetID = 0,
                    item = ItemInstance(
                        netID = netID,
                        metadata = 0u,
                        blockRuntimeID = 0,
                        count = 0u,
                        nbtData = CompoundTag(),
                        canBePlacedOn = emptyList(),
                        canBreak = emptyList(),
                    )
                )
            }

            val count = ProtoLE.UShort.deserialize(stream)
            val metadata = ProtoVAR.UInt.deserialize(stream)

            val hasNetID = Proto.Boolean.deserialize(stream)
            val stackNetID = if (hasNetID) {
                ProtoVAR.Int.deserialize(stream)
            } else {
                0
            }

            val blockRuntimeID = ProtoVAR.Int.deserialize(stream)

            // region UserDataBuffer

            val userDataBuffer = Buffer().apply {
                write(Proto.ByteString.deserialize(stream))
            }

            var nbtData = CompoundTag()
            val length = ProtoLE.Short.deserialize(userDataBuffer)
            if (length.toInt() == -1) {
                when (val version = Proto.UByte.deserialize(userDataBuffer)) {
                    1u.toUByte() -> {
                        nbtData = Tag.deserialize(userDataBuffer, TagSerialization.LE) as CompoundTag
                    }

                    else -> throw IllegalArgumentException("Invalid UserDataBuffer version: $version")
                }
            } else if (length > 0) {
                nbtData = Tag.deserialize(userDataBuffer, TagSerialization.LE) as CompoundTag
            }

            val canBePlacedOn = List(ProtoLE.UInt.deserialize(userDataBuffer).toInt()) {
                Proto.String.deserialize(userDataBuffer)
            }

            val canBreak = List(ProtoLE.UInt.deserialize(userDataBuffer).toInt()) {
                Proto.String.deserialize(userDataBuffer)
            }

            if (netID == ShieldID) {
                ProtoLE.Long.deserialize(userDataBuffer) // BlockingTicks (UNUSED?)
            }

            // endregion

            return ItemStack(
                stackNetID = stackNetID,
                item = ItemInstance(
                    netID = netID,
                    metadata = metadata,
                    blockRuntimeID = blockRuntimeID,
                    count = count,
                    nbtData = nbtData,
                    canBePlacedOn = canBePlacedOn,
                    canBreak = canBreak,
                )
            )
        }
    }
}
