package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorUniqueID

data class ChangeMobPropertyPacket(
    val actorID: Long,
    val property: String,
    val boolValue: Boolean,
    val stringValue: String,
    val intValue: Int,
    val floatValue: Float,
) : Packet(id) {
    companion object : PacketCodec<ChangeMobPropertyPacket> {
        override val id: Int = 182

        override fun deserialize(stream: Source): ChangeMobPropertyPacket {
            return ChangeMobPropertyPacket(
                actorID = ActorUniqueID.deserialize(stream),
                property = Proto.String.deserialize(stream),
                boolValue = Proto.Boolean.deserialize(stream),
                stringValue = Proto.String.deserialize(stream),
                intValue = ProtoVAR.Int.deserialize(stream),
                floatValue = ProtoLE.Float.deserialize(stream),
            )
        }

        override fun serialize(value: ChangeMobPropertyPacket, stream: Sink) {
            ActorUniqueID.serialize(value.actorID, stream)
            Proto.String.serialize(value.property, stream)
            Proto.Boolean.serialize(value.boolValue, stream)
            Proto.String.serialize(value.stringValue, stream)
            ProtoVAR.Int.serialize(value.intValue, stream)
            ProtoLE.Float.serialize(value.floatValue, stream)
        }
    }
}
