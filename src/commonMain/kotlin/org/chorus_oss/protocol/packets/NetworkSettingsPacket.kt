package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.types.PacketCompressionAlgorithm


data class NetworkSettingsPacket(
    val compressionThreshold: UShort,
    val compressionAlgorithm: PacketCompressionAlgorithm,
    val clientThrottle: Boolean,
    val clientThrottleThreshold: Byte,
    val clientThrottleScalar: Float,
) : Packet(id) {
    companion object : PacketCodec<NetworkSettingsPacket> {
        override val id: Int = 143

        override fun serialize(value: NetworkSettingsPacket, stream: Sink) {
            ProtoLE.UShort.serialize(value.compressionThreshold, stream)
            PacketCompressionAlgorithm.serialize(value.compressionAlgorithm, stream)
            Proto.Boolean.serialize(value.clientThrottle, stream)
            Proto.Byte.serialize(value.clientThrottleThreshold, stream)
            ProtoLE.Float.serialize(value.clientThrottleScalar, stream)
        }

        override fun deserialize(stream: Source): NetworkSettingsPacket {
            return NetworkSettingsPacket(
                compressionThreshold = ProtoLE.UShort.deserialize(stream),
                compressionAlgorithm = PacketCompressionAlgorithm.deserialize(stream),
                clientThrottle = Proto.Boolean.deserialize(stream),
                clientThrottleThreshold = Proto.Byte.deserialize(stream),
                clientThrottleScalar = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
