package org.chorus_oss.protocol.types.item

import kotlinx.io.*
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*

data class ItemInstance(
    val netID: Int,
    val metadata: UInt,
    val blockRuntimeID: Int,
    val count: UShort,
    val nbtData: CompoundTag,
    val canBePlacedOn: List<String>,
    val canBreak: List<String>,
) {
    companion object : ProtoCodec<ItemInstance> {
        private var _ShieldID: Int? = null

        var ShieldID: Int
            get() = _ShieldID
                ?: throw IllegalStateException("SHIELD_ID not set (should be the runtimeID of the Shield item)")
            set(value) {
                _ShieldID = value
            }

        override fun serialize(value: ItemInstance, stream: Sink) {
            ProtoVAR.Int.serialize(value.netID, stream)
            if (value.netID == 0) {
                return // ItemStack is invalid, no more data.
            }

            ProtoLE.UShort.serialize(value.count, stream)
            ProtoVAR.UInt.serialize(value.metadata, stream)
            ProtoVAR.Int.serialize(value.blockRuntimeID, stream)

            // region UserDataBuffer

            val userDataBuffer = Buffer()

            if (value.nbtData.isNotEmpty()) {
                ProtoLE.Short.serialize(-1, userDataBuffer) // Hardcoded
                Proto.UByte.serialize(1u, userDataBuffer) // Hardcoded
                Tag.serialize(value.nbtData, userDataBuffer, TagSerialization.LE, true)
            } else {
                ProtoLE.Short.serialize(0, userDataBuffer)
            }

            ProtoHelper.serializeList(value.canBePlacedOn, userDataBuffer, Proto.String)
            ProtoHelper.serializeList(value.canBreak, userDataBuffer, Proto.String)

            if (value.netID == ShieldID) {
                ProtoLE.Long.serialize(0, userDataBuffer)
            }

            // endregion
            val bytes = userDataBuffer.readByteArray()
            ProtoVAR.UInt.serialize(bytes.size.toUInt(), stream)
            stream.write(bytes)
        }

        override fun deserialize(stream: Source): ItemInstance {
            val netID = ProtoVAR.Int.deserialize(stream)
            if (netID == 0) {
                return ItemInstance(
                    netID = netID,
                    metadata = 0u,
                    blockRuntimeID = 0,
                    count = 0u,
                    nbtData = CompoundTag(),
                    canBePlacedOn = emptyList(),
                    canBreak = emptyList(),
                )
            }

            val count = ProtoLE.UShort.deserialize(stream)
            val metadata = ProtoVAR.UInt.deserialize(stream)
            val blockRuntimeID = ProtoVAR.Int.deserialize(stream)

            // region UserDataBuffer

            val userDataBytes = ByteArray(ProtoVAR.UInt.deserialize(stream).toInt())
            stream.readTo(userDataBytes)
            val userDataBuffer = Buffer()
            userDataBuffer.write(userDataBytes)

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

            val canBePlacedOn = ProtoHelper.deserializeList(userDataBuffer, Proto.String)
            val canBreak = ProtoHelper.deserializeList(userDataBuffer, Proto.String)

            if (netID == ShieldID) {
                ProtoLE.Long.deserialize(userDataBuffer) // BlockingTicks (UNUSED?)
            }

            // endregion

            return ItemInstance(
                netID = netID,
                metadata = metadata,
                blockRuntimeID = blockRuntimeID,
                count = count,
                nbtData = nbtData,
                canBePlacedOn = canBePlacedOn,
                canBreak = canBreak,
            )
        }
    }
}
