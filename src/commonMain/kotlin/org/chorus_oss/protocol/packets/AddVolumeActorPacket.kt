package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.shared.types.IVector3
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AddVolumeActorPacket(
    val actorRuntimeID: ActorRuntimeID,
    val components: CompoundTag,
    val jsonIdentifier: String,
    val instanceIdentifier: String,
    val minBounds: IVector3,
    val maxBounds: IVector3,
    val dimension: Int,
    val engineVersion: String,
) {
    companion object : PacketCodec<AddVolumeActorPacket> {
        override val id: Int
            get() = ProtocolInfo.ADD_VOLUME_ENTITY_PACKET

        override fun deserialize(stream: Buffer): AddVolumeActorPacket {
            return AddVolumeActorPacket(
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                components = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
                jsonIdentifier = Proto.String.deserialize(stream),
                instanceIdentifier = Proto.String.deserialize(stream),
                minBounds = IVector3.deserialize(stream),
                maxBounds = IVector3.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
                engineVersion = Proto.String.deserialize(stream),
            )
        }

        override fun serialize(value: AddVolumeActorPacket, stream: Buffer) {
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            Tag.serialize(value.components, stream, TagSerialization.NetLE, true)
            Proto.String.serialize(value.jsonIdentifier, stream)
            Proto.String.serialize(value.instanceIdentifier, stream)
            IVector3.serialize(value.minBounds, stream)
            IVector3.serialize(value.maxBounds, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            Proto.String.serialize(value.engineVersion, stream)
        }
    }
}
