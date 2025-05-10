package org.chorus_oss.protocol.types

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int

data class Color(
    val r: Byte = 0,
    val g: Byte = 0,
    val b: Byte = 0,
    val a: Byte = 0,
)

object FColorRGB : ProtoCodec<Color> {
    override fun serialize(value: Color, stream: Buffer) {
        ProtoLE.Float.serialize(value.r.toFloat() / 255f, stream)
        ProtoLE.Float.serialize(value.g.toFloat() / 255f, stream)
        ProtoLE.Float.serialize(value.b.toFloat() / 255f, stream)
    }

    override fun deserialize(stream: Buffer): Color {
        return Color(
            r = (ProtoLE.Float.deserialize(stream) * 255f).toInt().toByte(),
            g = (ProtoLE.Float.deserialize(stream) * 255f).toInt().toByte(),
            b = (ProtoLE.Float.deserialize(stream) * 255f).toInt().toByte(),
        )
    }
}

object IColorRGBA : ProtoCodec<Color> {
    override fun serialize(value: Color, stream: Buffer) {
        ProtoLE.Int.serialize(
            value.r.toInt() or (value.g.toInt() shl 8) or (value.b.toInt() shl 16) or (value.a.toInt() shl 24),
            stream
        )
    }

    override fun deserialize(stream: Buffer): Color {
        val int = ProtoLE.Int.deserialize(stream)
        return Color(
            r = int.toByte(),
            g = (int shr 8).toByte(),
            b = (int shr 16).toByte(),
            a = (int shr 24).toByte(),
        )
    }
}

object IVarColorRGBA : ProtoCodec<Color> {
    override fun serialize(value: Color, stream: Buffer) {
        ProtoVAR.Int.serialize(
            value.r.toInt() or (value.g.toInt() shl 8) or (value.b.toInt() shl 16) or (value.a.toInt() shl 24),
            stream
        )
    }

    override fun deserialize(stream: Buffer): Color {
        val int = ProtoVAR.Int.deserialize(stream)
        return Color(
            r = int.toByte(),
            g = (int shr 8).toByte(),
            b = (int shr 16).toByte(),
            a = (int shr 24).toByte(),
        )
    }
}
