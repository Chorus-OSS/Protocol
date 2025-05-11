package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float

data class ActorLink(
    val riddenActorUniqueID: ActorUniqueID,
    val riderActorUniqueID: ActorUniqueID,
    val type: Type,
    val immediate: Boolean,
    val riderInitiated: Boolean,
    val vehicleAngularVelocity: Float
) {
    companion object : ProtoCodec<ActorLink> {
        enum class Type {
            REMOVE,
            RIDER,
            PASSENGER;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: ActorLink, stream: Sink) {
            ActorUniqueID.serialize(value.riddenActorUniqueID, stream)
            ActorUniqueID.serialize(value.riderActorUniqueID, stream)
            Type.serialize(value.type, stream)
            Proto.Boolean.serialize(value.immediate, stream)
            Proto.Boolean.serialize(value.riderInitiated, stream)
            ProtoLE.Float.serialize(value.vehicleAngularVelocity, stream)
        }

        override fun deserialize(stream: Source): ActorLink {
            return ActorLink(
                riddenActorUniqueID = ActorUniqueID.deserialize(stream),
                riderActorUniqueID = ActorUniqueID.deserialize(stream),
                type = Type.deserialize(stream),
                immediate = Proto.Boolean.deserialize(stream),
                riderInitiated = Proto.Boolean.deserialize(stream),
                vehicleAngularVelocity = ProtoLE.Float.deserialize(stream)
            )
        }
    }
}
