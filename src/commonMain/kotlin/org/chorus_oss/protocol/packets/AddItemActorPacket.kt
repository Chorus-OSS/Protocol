package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.shared.types.Vector3f
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.actor_data.ActorDataMap
import org.chorus_oss.protocol.types.item.ItemInstance

data class AddItemActorPacket(
    val actorUniqueID: ActorUniqueID,
    val actorRuntimeID: ActorRuntimeID,
    val item: ItemInstance,
    val position: Vector3f,
    val velocity: Vector3f,
    val actorData: ActorDataMap,
    val fromFishing: Boolean,
) {
    companion object : PacketCodec<AddItemActorPacket> {
        override val id: Int
            get() = ProtocolInfo.ADD_ITEM_ACTOR_PACKET

        override fun deserialize(stream: Buffer): AddItemActorPacket {
            return AddItemActorPacket(
                actorUniqueID = ProtoVAR.Long.deserialize(stream),
                actorRuntimeID = ProtoVAR.ULong.deserialize(stream),
                item = ItemInstance.deserialize(stream),
                position = Vector3f.deserialize(stream),
                velocity = Vector3f.deserialize(stream),
                actorData = ActorDataMap.deserialize(stream),
                fromFishing = Proto.Boolean.deserialize(stream),
            )
        }

        override fun serialize(value: AddItemActorPacket, stream: Buffer) {
            ProtoVAR.Long.serialize(value.actorUniqueID, stream)
            ProtoVAR.ULong.serialize(value.actorRuntimeID, stream)
            ItemInstance.serialize(value.item, stream)
            Vector3f.serialize(value.position, stream)
            Vector3f.serialize(value.velocity, stream)
            ActorDataMap.serialize(value.actorData, stream)
            Proto.Boolean.serialize(value.fromFishing, stream)
        }
    }
}
