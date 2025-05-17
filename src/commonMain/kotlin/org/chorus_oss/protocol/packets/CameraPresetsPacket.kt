package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.camera.preset.CameraPreset

data class CameraPresetsPacket(
    val presets: List<CameraPreset>
) : Packet(id) {
    companion object : PacketCodec<CameraPresetsPacket> {
        override val id: Int
            get() = ProtocolInfo.CAMERA_PRESETS_PACKET

        override fun deserialize(stream: Source): CameraPresetsPacket {
            return CameraPresetsPacket(
                presets = ProtoHelper.deserializeList(stream, CameraPreset)
            )
        }

        override fun serialize(value: CameraPresetsPacket, stream: Sink) {
            ProtoHelper.serializeList(value.presets, stream, CameraPreset)
        }
    }
}
