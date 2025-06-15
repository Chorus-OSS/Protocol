package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.camera.aimassist.CameraAimAssistCategory
import org.chorus_oss.protocol.types.camera.aimassist.CameraAimAssistPreset
import org.chorus_oss.protocol.types.camera.aimassist.CameraAimAssistPresetsPacketOperation

data class CameraAimAssistPresetsPacket(
    val categories: List<CameraAimAssistCategory>,
    val presets: List<CameraAimAssistPreset>,
    val operation: CameraAimAssistPresetsPacketOperation,
) : Packet(id) {
    companion object : PacketCodec<CameraAimAssistPresetsPacket> {
        override val id: Int = 320

        override fun deserialize(stream: Source): CameraAimAssistPresetsPacket {
            return CameraAimAssistPresetsPacket(
                categories = ProtoHelper.deserializeList(stream, CameraAimAssistCategory),
                presets = ProtoHelper.deserializeList(stream, CameraAimAssistPreset),
                operation = CameraAimAssistPresetsPacketOperation.deserialize(stream)
            )
        }

        override fun serialize(value: CameraAimAssistPresetsPacket, stream: Sink) {
            ProtoHelper.serializeList(value.categories, stream, CameraAimAssistCategory)
            ProtoHelper.serializeList(value.presets, stream, CameraAimAssistPreset)
            CameraAimAssistPresetsPacketOperation.serialize(value.operation, stream)
        }
    }
}
