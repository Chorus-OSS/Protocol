package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String


data class SettingsCommandPacket(
    val commandLine: String,
    val suppressOutput: Boolean
) : Packet(id) {
    companion object : PacketCodec<SettingsCommandPacket> {
        override val id: Int = 140

        override fun serialize(value: SettingsCommandPacket, stream: Sink) {
            Proto.String.serialize(value.commandLine, stream)
            Proto.Boolean.serialize(value.suppressOutput, stream)
        }

        override fun deserialize(stream: Source): SettingsCommandPacket {
            return SettingsCommandPacket(
                commandLine = Proto.String.deserialize(stream),
                suppressOutput = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
