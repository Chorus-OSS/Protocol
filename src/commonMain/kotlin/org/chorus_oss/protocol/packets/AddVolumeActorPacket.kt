package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3

data class AddVolumeActorPacket(
    val actorRuntimeID: ActorRuntimeID,
    val components: CompoundTag,
    val jsonIdentifier: String,
    val instanceIdentifier: String,
    val minBounds: IVector3,
    val maxBounds: IVector3,
    val dimension: Int,
    val engineVersion: String,
) : Packet(id) {
    companion object : PacketCodec<AddVolumeActorPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.ADD_VOLUME_ENTITY_PACKET

        override fun deserialize(stream: Source): AddVolumeActorPacket {
            return AddVolumeActorPacket(
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                components = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
                jsonIdentifier = Proto.String.deserialize(stream),
                instanceIdentifier = Proto.String.deserialize(stream),
                minBounds = UIVector3.deserialize(stream),
                maxBounds = UIVector3.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
                engineVersion = Proto.String.deserialize(stream),
            )
        }

        override fun serialize(value: AddVolumeActorPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            Tag.serialize(value.components, stream, TagSerialization.NetLE, true)
            Proto.String.serialize(value.jsonIdentifier, stream)
            Proto.String.serialize(value.instanceIdentifier, stream)
            UIVector3.serialize(value.minBounds, stream)
            UIVector3.serialize(value.maxBounds, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            Proto.String.serialize(value.engineVersion, stream)
        }
    }
}
