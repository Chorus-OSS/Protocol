package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.camera.instruction.CameraFadeInstruction
import org.chorus_oss.protocol.types.camera.instruction.CameraSetInstruction
import org.chorus_oss.protocol.types.camera.instruction.CameraTargetInstruction

data class CameraInstructionPacket(
    var set: CameraSetInstruction? = null,
    var clear: Boolean? = null,
    var fade: CameraFadeInstruction? = null,
    var target: CameraTargetInstruction? = null,
    var removeTarget: Boolean? = null,
) {
    companion object : PacketCodec<CameraInstructionPacket> {
        override val id: Int
            get() = ProtocolInfo.CAMERA_INSTRUCTION_PACKET

        override fun deserialize(stream: Source): CameraInstructionPacket {
            return CameraInstructionPacket(
                set = ProtoHelper.deserializeNullable(stream, CameraSetInstruction::deserialize),
                clear = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize),
                fade = ProtoHelper.deserializeNullable(stream, CameraFadeInstruction::deserialize),
                target = ProtoHelper.deserializeNullable(stream, CameraTargetInstruction::deserialize),
                removeTarget = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize),
            )
        }

        override fun serialize(value: CameraInstructionPacket, stream: Sink) {
            ProtoHelper.serializeNullable(value.set, stream, CameraSetInstruction::serialize)
            ProtoHelper.serializeNullable(value.clear, stream, Proto.Boolean::serialize)
            ProtoHelper.serializeNullable(value.fade, stream, CameraFadeInstruction::serialize)
            ProtoHelper.serializeNullable(value.target, stream, CameraTargetInstruction::serialize)
            ProtoHelper.serializeNullable(value.removeTarget, stream, Proto.Boolean::serialize)
        }
    }
}
