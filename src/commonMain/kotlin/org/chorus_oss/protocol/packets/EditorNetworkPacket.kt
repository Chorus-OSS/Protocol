package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean

data class EditorNetworkPacket(
    val routeToManager: Boolean,
    val payload: CompoundTag
) : Packet(id) {
    companion object : PacketCodec<EditorNetworkPacket> {
        override val id: Int
            get() = ProtocolInfo.EDITOR_NETWORK_PACKET

        override fun serialize(value: EditorNetworkPacket, stream: Sink) {
            Proto.Boolean.serialize(value.routeToManager, stream)
            Tag.serialize(value.payload, stream, TagSerialization.NetLE, true)
        }

        override fun deserialize(stream: Source): EditorNetworkPacket {
            return EditorNetworkPacket(
                routeToManager = Proto.Boolean.deserialize(stream),
                payload = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag
            )
        }
    }
}
