package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Short


data class CompletedUsingItemPacket(
    val itemID: Short,
    val itemUseMethod: ItemUseMethod,
) : Packet(id) {
    companion object : PacketCodec<CompletedUsingItemPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class ItemUseMethod(val id: Int) {
            UNKNOWN(-1),
            EQUIP_ARMOR(0),
            EAT(1),
            ATTACK(2),
            CONSUME(3),
            THROW(4),
            SHOOT(5),
            PLACE(6),
            FILL_BOTTLE(7),
            FILL_BUCKET(8),
            POUR_BUCKET(9),
            USE_TOOL(10),
            INTERACT(11),
            RETRIEVED(12),
            DYED(13),
            TRADED(14),
            BRUSHING_COMPLETED(15),
            OPENED_VAULT(16);

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

        override val id: Int
            get() = ProtocolInfo.COMPLETED_USING_ITEM_PACKET

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
