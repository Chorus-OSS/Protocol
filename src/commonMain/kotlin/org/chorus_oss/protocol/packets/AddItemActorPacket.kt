package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.Vector3f
import org.chorus_oss.protocol.types.actor_data.ActorDataMap
import org.chorus_oss.protocol.types.item.ItemStack

data class AddItemActorPacket(
    val actorUniqueID: Long,
    val actorRuntimeID: ULong,
    val item: ItemStack,
    val position: Vector3f,
    val velocity: Vector3f,
    val actorData: ActorDataMap,
    val fromFishing: Boolean,
) : Packet(id) {
    companion object : PacketCodec<AddItemActorPacket> {
        override val id: Int = 15

        override fun deserialize(stream: Source): AddItemActorPacket {
            return AddItemActorPacket(
                actorUniqueID = ActorUniqueID.deserialize(stream),
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                item = ItemStack.deserialize(stream),
                position = Vector3f.deserialize(stream),
                velocity = Vector3f.deserialize(stream),
                actorData = ActorDataMap.deserialize(stream),
                fromFishing = Proto.Boolean.deserialize(stream),
            )
        }

        override fun serialize(value: AddItemActorPacket, stream: Sink) {
            ActorUniqueID.serialize(value.actorUniqueID, stream)
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            ItemStack.serialize(value.item, stream)
            Vector3f.serialize(value.position, stream)
            Vector3f.serialize(value.velocity, stream)
            ActorDataMap.serialize(value.actorData, stream)
            Proto.Boolean.serialize(value.fromFishing, stream)
        }
    }
}
