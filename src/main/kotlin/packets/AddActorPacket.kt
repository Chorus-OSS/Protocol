package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.entity.Attribute
import org.chorus_oss.chorus.entity.data.EntityDataMap
import org.chorus_oss.chorus.math.Vector2f
import org.chorus_oss.chorus.math.Vector3f

import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.EntityLink
import org.chorus_oss.protocol.types.PropertySyncData
import org.chorus_oss.chorus.utils.Binary
import org.chorus_oss.protocol.ProtocolInfo

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
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.targetActorID)
        byteBuf.writeActorRuntimeID(this.targetRuntimeID)
        byteBuf.writeString(this.actorType)
        byteBuf.writeVector3f(this.position)
        byteBuf.writeVector3f(this.velocity)
        byteBuf.writeVector2f(this.rotation)
        byteBuf.writeFloatLE(this.yHeadRotation)
        byteBuf.writeFloatLE(this.yBodyRotation)
        byteBuf.writeAttributeList(this.attributeList)
        byteBuf.writeBytes(Binary.writeEntityData(this.actorData))
        byteBuf.writePropertySyncData(this.syncedProperties)
        byteBuf.writeArray(this.actorLinks) { buf, link -> buf.writeEntityLink(link) }
    }

    override fun pid(): Int {
        return ProtocolInfo.ADD_ACTOR_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
