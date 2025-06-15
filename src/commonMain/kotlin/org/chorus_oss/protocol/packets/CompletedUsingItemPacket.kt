package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Short


data class CompletedUsingItemPacket(
    val itemID: Short,
    val itemUseMethod: ItemUseMethod,
) : Packet(id) {
    companion object : PacketCodec<CompletedUsingItemPacket> {
        enum class ItemUseMethod(val id: Int) {
            Unknown(-1),
            EquipArmor(0),
            Eat(1),
            Attack(2),
            Consume(3),
            Throw(4),
            Shoot(5),
            Place(6),
            FillBottle(7),
            FillBucket(8),
            PourBucket(9),
            UseTool(10),
            Interact(11),
            Retrieved(12),
            Dyed(13),
            Traded(14),
            BrushingCompleted(15),
            OpenedVault(16);

            companion object : ProtoCodec<ItemUseMethod> {
                override fun serialize(
                    value: ItemUseMethod,
                    stream: Sink
                ) {
                    ProtoLE.Int.serialize(value.id, stream)
                }

                override fun deserialize(stream: Source): ItemUseMethod {
                    return ProtoLE.Int.deserialize(stream).let {
                        entries.find { e -> e.id == it }!!
                    }
                }

            }
        }

        override val id: Int = 142

        override fun serialize(
            value: CompletedUsingItemPacket,
            stream: Sink
        ) {
            ProtoLE.Short.serialize(value.itemID, stream)
            ItemUseMethod.serialize(value.itemUseMethod, stream)
        }

        override fun deserialize(stream: Source): CompletedUsingItemPacket {
            return CompletedUsingItemPacket(
                itemID = ProtoLE.Short.deserialize(stream),
                itemUseMethod = ItemUseMethod.deserialize(stream),
            )
        }
    }
}
