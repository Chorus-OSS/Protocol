package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.PackSetting
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ServerboundPackSettingChangePacket(
    val packID: Uuid,
    val packSetting: PackSetting,
) {
    companion object : PacketCodec<ServerboundPackSettingChangePacket> {
        override val id: Int = 329

        override fun serialize(
            value: ServerboundPackSettingChangePacket,
            stream: Sink
        ) {
            Proto.Uuid.serialize(value.packID, stream)
            PackSetting.serialize(value.packSetting, stream)
        }

        override fun deserialize(stream: Source): ServerboundPackSettingChangePacket {
            return ServerboundPackSettingChangePacket(
                packID = Proto.Uuid.deserialize(stream),
                packSetting = PackSetting.deserialize(stream),
            )
        }
    }
}
