package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.Vector3f


data class SpawnParticleEffectPacket(
    val dimension: Byte,
    val entityUniqueID: Long,
    val position: Vector3f,
    val identifier: String,
    val moLangVariablesJSON: String?
) : Packet(id) {
    companion object : PacketCodec<SpawnParticleEffectPacket> {
        override val id: Int = 118

        override fun serialize(
            value: SpawnParticleEffectPacket,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.dimension, stream)
            ActorUniqueID.serialize(value.entityUniqueID, stream)
            Vector3f.serialize(value.position, stream)
            Proto.String.serialize(value.identifier, stream)
            ProtoHelper.serializeNullable(value.moLangVariablesJSON, stream, Proto.String)
        }

        override fun deserialize(stream: Source): SpawnParticleEffectPacket {
            return SpawnParticleEffectPacket(
                dimension = Proto.Byte.deserialize(stream),
                entityUniqueID = ActorUniqueID.deserialize(stream),
                position = Vector3f.deserialize(stream),
                identifier = Proto.String.deserialize(stream),
                moLangVariablesJSON = ProtoHelper.deserializeNullable(stream, Proto.String),
            )
        }
    }
}
