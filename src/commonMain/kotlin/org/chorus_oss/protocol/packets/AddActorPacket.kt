package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.shared.types.Vector2f
import org.chorus_oss.protocol.shared.types.Vector3f
import org.chorus_oss.protocol.types.ActorLink
import org.chorus_oss.protocol.types.ActorProperties
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.actor_data.ActorDataMap
import org.chorus_oss.protocol.types.attribute.AttributeValue

data class AddActorPacket(
    val actorUniqueID: ActorUniqueID,
    val actorRuntimeID: ActorRuntimeID,
    val actorType: String,
    val position: Vector3f,
    val velocity: Vector3f,
    val rotation: Vector2f,
    val headYaw: Float,
    val bodyYaw: Float,
    val attributes: List<AttributeValue>,
    val actorData: ActorDataMap,
    val actorProperties: ActorProperties,
    val actorLinks: List<ActorLink>
) {
    companion object : PacketCodec<AddActorPacket> {
        override val id: Int
            get() = ProtocolInfo.ADD_ACTOR_PACKET

        override fun deserialize(stream: Buffer): AddActorPacket {
            return AddActorPacket(
                actorUniqueID = ProtoVAR.Long.deserialize(stream),
                actorRuntimeID = ProtoVAR.Long.deserialize(stream),
                actorType = Proto.String.deserialize(stream),
                position = Vector3f.deserialize(stream),
                velocity = Vector3f.deserialize(stream),
                rotation = Vector2f.deserialize(stream),
                headYaw = ProtoLE.Float.deserialize(stream),
                bodyYaw = ProtoLE.Float.deserialize(stream),
                attributes = ProtoHelper.deserializeList(stream, AttributeValue::deserialize),
                actorData = ActorDataMap.deserialize(stream),
                actorProperties = ActorProperties.deserialize(stream),
                actorLinks = ProtoHelper.deserializeList(stream, ActorLink::deserialize)
            )
        }

        override fun serialize(value: AddActorPacket, stream: Buffer) {
            ProtoVAR.Long.serialize(value.actorUniqueID, stream)
            ProtoVAR.Long.serialize(value.actorRuntimeID, stream)
            Proto.String.serialize(value.actorType, stream)
            Vector3f.serialize(value.position, stream)
            Vector3f.serialize(value.velocity, stream)
            Vector2f.serialize(value.rotation, stream)
            ProtoLE.Float.serialize(value.headYaw, stream)
            ProtoLE.Float.serialize(value.bodyYaw, stream)
            ProtoHelper.serializeList(value.attributes, stream, AttributeValue::serialize)
            ActorDataMap.serialize(value.actorData, stream)
            ActorProperties.serialize(value.actorProperties, stream)
            ProtoHelper.serializeList(value.actorLinks, stream, ActorLink::serialize)
        }
    }
}
