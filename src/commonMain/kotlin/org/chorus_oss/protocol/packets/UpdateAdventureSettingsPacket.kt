package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean


data class UpdateAdventureSettingsPacket(
    val noPvM: Boolean,
    val noMvP: Boolean,
    val immutableWorld: Boolean,
    val showNameTags: Boolean,
    val autoJump: Boolean,
) : Packet(id) {
    companion object : PacketCodec<UpdateAdventureSettingsPacket> {
        override val id: Int
            get() = ProtocolInfo.UPDATE_ADVENTURE_SETTINGS_PACKET

        override fun serialize(
            value: UpdateAdventureSettingsPacket,
            stream: Sink
        ) {
            Proto.Boolean.serialize(value.noPvM, stream)
            Proto.Boolean.serialize(value.noMvP, stream)
            Proto.Boolean.serialize(value.immutableWorld, stream)
            Proto.Boolean.serialize(value.showNameTags, stream)
            Proto.Boolean.serialize(value.autoJump, stream)
        }

        override fun deserialize(stream: Source): UpdateAdventureSettingsPacket {
            return UpdateAdventureSettingsPacket(
                noPvM = Proto.Boolean.deserialize(stream),
                noMvP = Proto.Boolean.deserialize(stream),
                immutableWorld = Proto.Boolean.deserialize(stream),
                showNameTags = Proto.Boolean.deserialize(stream),
                autoJump = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
