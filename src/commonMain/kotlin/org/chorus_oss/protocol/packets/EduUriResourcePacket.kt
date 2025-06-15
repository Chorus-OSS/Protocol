package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.EduSharedUriResource


data class EduUriResourcePacket(
    val resource: EduSharedUriResource,
) : Packet(id) {
    companion object : PacketCodec<EduUriResourcePacket> {
        override val id: Int = 170

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
