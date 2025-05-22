package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String


data class StopSoundPacket(
    val soundName: String,
    val stopAll: Boolean,
    val stopLegacyMusic: Boolean,
) : Packet(id) {
    companion object : PacketCodec<StopSoundPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.STOP_SOUND_PACKET

        override fun serialize(value: StopSoundPacket, stream: Sink) {
            Proto.String.serialize(value.soundName, stream)
            Proto.Boolean.serialize(value.stopAll, stream)
            Proto.Boolean.serialize(value.stopLegacyMusic, stream)
        }

        override fun deserialize(stream: Source): StopSoundPacket {
            return StopSoundPacket(
                soundName = Proto.String.deserialize(stream),
                stopAll = Proto.Boolean.deserialize(stream),
                stopLegacyMusic = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
