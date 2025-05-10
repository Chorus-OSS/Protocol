package org.chorus_oss.protocol.shared.types

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

data class IVector3(
    var x: Int = 0,
    var y: Int = 0,
    var z: Int = 0
) {
    companion object : ProtoCodec<IVector3> {
        override fun serialize(value: IVector3, stream: Buffer) {
            ProtoVAR.Int.serialize(value.x, stream)
            ProtoVAR.Int.serialize(value.y, stream)
            ProtoVAR.Int.serialize(value.z, stream)
        }

        override fun deserialize(stream: Buffer): IVector3 {
            return IVector3(
                x = ProtoVAR.Int.deserialize(stream),
                y = ProtoVAR.Int.deserialize(stream),
                z = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}

object UIVector3 : ProtoCodec<IVector3> {
    override fun serialize(value: IVector3, stream: Buffer) {
        ProtoVAR.Int.serialize(value.x, stream)
        ProtoVAR.UInt.serialize(value.y.toUInt(), stream)
        ProtoVAR.Int.serialize(value.z, stream)
    }

    override fun deserialize(stream: Buffer): IVector3 {
        return IVector3(
            x = ProtoVAR.Int.deserialize(stream),
            y = ProtoVAR.UInt.deserialize(stream).toInt(),
            z = ProtoVAR.Int.deserialize(stream),
        )
    }
}
