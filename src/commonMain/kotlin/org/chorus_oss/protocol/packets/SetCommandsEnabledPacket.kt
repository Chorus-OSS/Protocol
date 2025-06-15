package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean


data class SetCommandsEnabledPacket(
    val enabled: Boolean,
) : Packet(id) {
    companion object : PacketCodec<SetCommandsEnabledPacket> {
        override val id: Int = 59

        override fun serialize(
            value: SetCommandsEnabledPacket,
            stream: Sink
        ) {
            Proto.Boolean.serialize(value.enabled, stream)
        }

        override fun deserialize(stream: Source): SetCommandsEnabledPacket {
            return SetCommandsEnabledPacket(
                enabled = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
