package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.camera.preset.CameraPreset

data class CameraPresetsPacket(
    val presets: List<CameraPreset>
) {
    companion object : PacketCodec<CameraPresetsPacket> {
        override val id: Int
            get() = ProtocolInfo.CAMERA_PRESETS_PACKET

        override fun deserialize(stream: Buffer): CameraPresetsPacket {
            return CameraPresetsPacket(
                presets = ProtoHelper.deserializeList(stream, CameraPreset::deserialize)
            )
        }

        override fun serialize(value: CameraPresetsPacket, stream: Buffer) {
            ProtoHelper.serializeList(value.presets, stream, CameraPreset::serialize)
        }
    }
}
