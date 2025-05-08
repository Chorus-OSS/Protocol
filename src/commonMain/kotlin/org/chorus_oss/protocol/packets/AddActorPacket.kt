package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.chorus.entity.Attribute
import org.chorus_oss.chorus.entity.data.EntityDataMap
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.shared.types.Vector2f
import org.chorus_oss.protocol.shared.types.Vector3f
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.EntityLink
import org.chorus_oss.protocol.types.PropertySyncData

data class AddActorPacket(
    val targetActorID: ActorUniqueID,
    val targetRuntimeID: ActorRuntimeID,
    val actorType: String,
    val position: Vector3f,
    val velocity: Vector3f,
    val rotation: Vector2f,
    val yHeadRotation: Float,
    val yBodyRotation: Float,
    val attributeList: List<Attribute>,
    val actorData: EntityDataMap,
    val syncedProperties: PropertySyncData,
    val actorLinks: List<EntityLink>
) {
//    override fun encode(byteBuf: ByteBuf) {
//        byteBuf.writeActorUniqueID(this.targetActorID)
//        byteBuf.writeActorRuntimeID(this.targetRuntimeID)
//        byteBuf.writeString(this.actorType)
//        byteBuf.writeVector3f(this.position)
//        byteBuf.writeVector3f(this.velocity)
//        byteBuf.writeVector2f(this.rotation)
//        byteBuf.writeFloatLE(this.yHeadRotation)
//        byteBuf.writeFloatLE(this.yBodyRotation)
//        byteBuf.writeAttributeList(this.attributeList)
//        byteBuf.writeBytes(Binary.writeEntityData(this.actorData))
//        byteBuf.writePropertySyncData(this.syncedProperties)
//        byteBuf.writeArray(this.actorLinks) { buf, link -> buf.writeEntityLink(link) }
//    }

    companion object : PacketCodec<AddActorPacket> {
        override val id: Int
            get() = ProtocolInfo.ADD_ACTOR_PACKET

        override fun deserialize(stream: Buffer): AddActorPacket {
            TODO("Not yet implemented")
        }

        override fun serialize(value: AddActorPacket, stream: Buffer) {
            ProtoVAR.Long.serialize(value.targetActorID, stream)
            ProtoVAR.Long.serialize(value.targetRuntimeID, stream)
            Proto.String.serialize(value.actorType, stream)
            Vector3f.serialize(value.position, stream)
            Vector3f.serialize(value.velocity, stream)
            Vector2f.serialize(value.rotation, stream)
            ProtoLE.Float.serialize(value.yHeadRotation, stream)
            ProtoLE.Float.serialize(value.yBodyRotation, stream)
            // TODO: attributeList
            // TODO: actorData
            // TODO: syncedProperties
            ProtoHelper.writeList(value.actorLinks, stream) { into, value ->
                // TODO: actorLink
            }
        }
    }
}
