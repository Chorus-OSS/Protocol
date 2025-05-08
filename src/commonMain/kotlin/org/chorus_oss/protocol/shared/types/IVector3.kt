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
    override fun toString(): String {
        return "IVector3(x=" + this.x + ",y=" + this.y + ",z=" + this.z + ")"
    }

    companion object : ProtoCodec<IVector3> {
        const val SIDE_DOWN: Int = 0
        const val SIDE_UP: Int = 1
        const val SIDE_NORTH: Int = 2
        const val SIDE_SOUTH: Int = 3
        const val SIDE_WEST: Int = 4
        const val SIDE_EAST: Int = 5

        fun getOppositeSide(side: Int): Int {
            return when (side) {
                SIDE_DOWN -> SIDE_UP
                SIDE_UP -> SIDE_DOWN
                SIDE_NORTH -> SIDE_SOUTH
                SIDE_SOUTH -> SIDE_NORTH
                SIDE_WEST -> SIDE_EAST
                SIDE_EAST -> SIDE_WEST
                else -> -1
            }
        }

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
}
