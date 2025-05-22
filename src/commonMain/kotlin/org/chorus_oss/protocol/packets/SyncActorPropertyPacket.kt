package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec


data class SyncActorPropertyPacket(
    val data: CompoundTag
) : Packet(id) {
    companion object : PacketCodec<SyncActorPropertyPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.SYNC_ENTITY_PROPERTY_PACKET

        override fun serialize(
            value: SyncActorPropertyPacket,
            stream: Sink
        ) {
            Tag.serialize(value.data, stream, TagSerialization.NetLE, true)
        }

        override fun deserialize(stream: Source): SyncActorPropertyPacket {
            return SyncActorPropertyPacket(
                data = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
            )
        }
    }
}
