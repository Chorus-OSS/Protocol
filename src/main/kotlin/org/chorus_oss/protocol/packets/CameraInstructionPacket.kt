package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.camera.data.Ease
import org.chorus_oss.chorus.camera.data.Time
import org.chorus_oss.chorus.camera.instruction.CameraInstruction
import org.chorus_oss.chorus.camera.instruction.impl.ClearInstruction
import org.chorus_oss.chorus.camera.instruction.impl.FadeInstruction
import org.chorus_oss.chorus.camera.instruction.impl.SetInstruction
import org.chorus_oss.chorus.camera.instruction.impl.TargetInstruction

import java.awt.Color

data class CameraInstructionPacket(
    var set: SetInstruction? = null,
    var clear: ClearInstruction? = null,
    var fade: FadeInstruction? = null,
    var target: TargetInstruction? = null,
    var removeTarget: Boolean? = null,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeNotNull(set) { s ->
            byteBuf.writeIntLE(s.preset!!.getId())
            byteBuf.writeNotNull(s.ease) { e -> this.writeEase(byteBuf, e) }
            byteBuf.writeNotNull(s.pos, byteBuf::writeVector3f)
            byteBuf.writeNotNull(s.rot, byteBuf::writeVector2f)
            byteBuf.writeNotNull(s.facing, byteBuf::writeVector3f)
            byteBuf.writeNotNull(s.viewOffset, byteBuf::writeVector2f)
            byteBuf.writeNotNull(s.entityOffset, byteBuf::writeVector3f)
            byteBuf.writeOptional(s.defaultPreset) { value -> byteBuf.writeBoolean(value) }
        }

        byteBuf.writeNotNull(clear) { byteBuf.writeBoolean(true) }

        byteBuf.writeNotNull(fade) { f ->
            byteBuf.writeNotNull(f.time) { t -> this.writeTimeData(byteBuf, t) }
            byteBuf.writeNotNull(f.color) { c -> this.writeColor(byteBuf, c) }
        }

        byteBuf.writeNotNull(target) { target ->
            byteBuf.writeNotNull(target.targetCenterOffset, byteBuf::writeVector3f)
            byteBuf.writeLongLE(target.uniqueEntityId)
        }

        byteBuf.writeNotNull(removeTarget) { byteBuf.writeBoolean(it) }
    }

    fun setInstruction(instruction: CameraInstruction) {
        when (instruction) {
            is SetInstruction -> this.set = instruction
            is FadeInstruction -> this.fade = instruction
            is ClearInstruction -> this.clear = instruction
            is TargetInstruction -> this.target = instruction
        }
    }

    protected fun writeEase(byteBuf: ByteBuf, ease: Ease) {
        byteBuf.writeByte(ease.easeType.ordinal.toByte().toInt())
        byteBuf.writeFloatLE(ease.time)
    }

    protected fun writeTimeData(byteBuf: ByteBuf, time: Time) {
        byteBuf.writeFloatLE(time.fadeIn)
        byteBuf.writeFloatLE(time.hold)
        byteBuf.writeFloatLE(time.fadeOut)
    }

    protected fun writeColor(byteBuf: ByteBuf, color: Color) {
        byteBuf.writeFloatLE(color.red / 255f)
        byteBuf.writeFloatLE(color.green / 255f)
        byteBuf.writeFloatLE(color.blue / 255f)
    }

    override fun pid(): Int {
        return ProtocolInfo.CAMERA_INSTRUCTION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
