package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String


data class CodeBuilderPacket(
    val url: String,
    val shouldOpenCodeBuilder: Boolean,
) : Packet(id) {
    companion object : PacketCodec<CodeBuilderPacket> {
        override val id: Int
            get() = ProtocolInfo.CODE_BUILDER_PACKET

        override fun deserialize(stream: Source): CodeBuilderPacket {
            return CodeBuilderPacket(
                url = Proto.String.deserialize(stream),
                shouldOpenCodeBuilder = Proto.Boolean.deserialize(stream),
            )
        }

        override fun serialize(value: CodeBuilderPacket, stream: Sink) {
            Proto.String.serialize(value.url, stream)
            Proto.Boolean.serialize(value.shouldOpenCodeBuilder, stream)
        }
    }
}
