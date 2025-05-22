package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.Vector3f

data class ChangeDimensionPacket(
    val dimension: Int,
    val position: Vector3f,
    val respawn: Boolean,
    val loadingScreenID: Int? = null,
) : Packet(id) {
    companion object : PacketCodec<ChangeDimensionPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.CHANGE_DIMENSION_PACKET

        override fun deserialize(stream: Source): ChangeDimensionPacket {
            return ChangeDimensionPacket(
                dimension = ProtoVAR.Int.deserialize(stream),
                position = Vector3f.deserialize(stream),
                respawn = Proto.Boolean.deserialize(stream),
                loadingScreenID = ProtoHelper.deserializeNullable(stream, ProtoLE.Int)
            )
        }

        override fun serialize(value: ChangeDimensionPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.dimension, stream)
            Vector3f.serialize(value.position, stream)
            Proto.Boolean.serialize(value.respawn, stream)
            ProtoHelper.serializeNullable(value.loadingScreenID, stream, ProtoLE.Int)
        }
    }
}
