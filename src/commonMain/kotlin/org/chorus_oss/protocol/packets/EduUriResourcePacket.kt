package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.EduSharedUriResource


data class EduUriResourcePacket(
    val resource: EduSharedUriResource,
) : Packet(id) {
    companion object : PacketCodec<EduUriResourcePacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.EDU_URI_RESOURCE_PACKET

        override fun serialize(value: EduUriResourcePacket, stream: Sink) {
            EduSharedUriResource.serialize(value.resource, stream)
        }

        override fun deserialize(stream: Source): EduUriResourcePacket {
            return EduUriResourcePacket(
                resource = EduSharedUriResource.deserialize(stream),
            )
        }
    }
}
