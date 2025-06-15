package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt


data class UnlockedRecipesPacket(
    val unlockType: UnlockType,
    val recipes: List<String>
) : Packet(id) {
    companion object : PacketCodec<UnlockedRecipesPacket> {
        enum class UnlockType {
            Empty,
            InitiallyUnlocked,
            NewlyUnlocked,
            RemoveUnlocked,
            RemoveAllUnlocked;

            companion object : ProtoCodec<UnlockType> {
                override fun serialize(
                    value: UnlockType,
                    stream: Sink
                ) {
                    ProtoLE.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): UnlockType {
                    return entries[ProtoLE.UInt.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 199

        override fun serialize(value: UnlockedRecipesPacket, stream: Sink) {
            UnlockType.serialize(value.unlockType, stream)
            ProtoHelper.serializeList(value.recipes, stream, Proto.String)
        }

        override fun deserialize(stream: Source): UnlockedRecipesPacket {
            return UnlockedRecipesPacket(
                unlockType = UnlockType.deserialize(stream),
                recipes = ProtoHelper.deserializeList(stream, Proto.String),
            )
        }
    }
}
