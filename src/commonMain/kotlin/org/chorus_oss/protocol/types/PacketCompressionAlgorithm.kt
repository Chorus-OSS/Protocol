package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.UShort

enum class PacketCompressionAlgorithm(val net: UShort) {
    Zlib(0u),
    Snappy(1u),
    None(0xFFFFu);

    companion object : ProtoCodec<PacketCompressionAlgorithm> {
        override fun serialize(
            value: PacketCompressionAlgorithm,
            stream: Sink
        ) {
            ProtoLE.UShort.serialize(value.net, stream)
        }

        override fun deserialize(stream: Source): PacketCompressionAlgorithm {
            return ProtoLE.UShort.deserialize(stream).let {
                entries.find { e -> e.net == it }!!
            }
        }
    }
}