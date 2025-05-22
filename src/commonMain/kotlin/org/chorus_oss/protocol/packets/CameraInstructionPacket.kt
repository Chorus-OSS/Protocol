package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
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
) : Packet(id) {
    companion object : PacketCodec<CameraInstructionPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.CAMERA_INSTRUCTION_PACKET

        override fun deserialize(stream: Source): CameraInstructionPacket {
            return CameraInstructionPacket(
                set = ProtoHelper.deserializeNullable(stream, CameraSetInstruction),
                clear = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
                fade = ProtoHelper.deserializeNullable(stream, CameraFadeInstruction),
                target = ProtoHelper.deserializeNullable(stream, CameraTargetInstruction),
                removeTarget = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
            )
        }

        override fun serialize(value: CameraInstructionPacket, stream: Sink) {
            ProtoHelper.serializeNullable(value.set, stream, CameraSetInstruction)
            ProtoHelper.serializeNullable(value.clear, stream, Proto.Boolean)
            ProtoHelper.serializeNullable(value.fade, stream, CameraFadeInstruction)
            ProtoHelper.serializeNullable(value.target, stream, CameraTargetInstruction)
            ProtoHelper.serializeNullable(value.removeTarget, stream, Proto.Boolean)
        }
    }
}
