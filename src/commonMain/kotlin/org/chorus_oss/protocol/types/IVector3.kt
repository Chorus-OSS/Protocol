package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt

data class IVector3(
    var x: Int = 0,
    var y: Int = 0,
    var z: Int = 0
) {
    companion object : ProtoCodec<IVector3> {
        override fun serialize(value: IVector3, stream: Sink) {
            ProtoVAR.Int.serialize(value.x, stream)
            ProtoVAR.Int.serialize(value.y, stream)
            ProtoVAR.Int.serialize(value.z, stream)
        }

        override fun deserialize(stream: Source): IVector3 {
            return IVector3(
                x = ProtoVAR.Int.deserialize(stream),
                y = ProtoVAR.Int.deserialize(stream),
                z = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}

object UIVector3 : ProtoCodec<IVector3> {
    override fun serialize(value: IVector3, stream: Sink) {
        ProtoVAR.Int.serialize(value.x, stream)
        ProtoVAR.UInt.serialize(value.y.toUInt(), stream)
        ProtoVAR.Int.serialize(value.z, stream)
    }

    override fun deserialize(stream: Source): IVector3 {
        return IVector3(
            x = ProtoVAR.Int.deserialize(stream),
            y = ProtoVAR.UInt.deserialize(stream).toInt(),
            z = ProtoVAR.Int.deserialize(stream),
        )
    }
}
