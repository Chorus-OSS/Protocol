package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

data class Vector3f(
    var x: Float = 0f,
    var y: Float = 0f,
    var z: Float = 0f
) {
    fun setX(x: Float): Vector3f {
        this.x = x
        return this
    }

    fun setY(y: Float): Vector3f {
        this.y = y
        return this
    }

    fun setZ(z: Float): Vector3f {
        this.z = z
        return this
    }

    val floorX: Int
        get() = floor(this.x).toInt()

    val floorY: Int
        get() = floor(this.y).toInt()

    val floorZ: Int
        get() = floor(this.z).toInt()

    fun add(x: Float = 0f, y: Float = 0f, z: Float = 0f): Vector3f {
        return Vector3f(this.x + x, this.y + y, this.z + z)
    }

    fun add(x: Vector3f): Vector3f {
        return Vector3f(this.x + x.x, this.y + x.y, this.z + x.z)
    }

    fun subtract(x: Float = 0f, y: Float = 0f, z: Float = 0f): Vector3f {
        return this.add(-x, -y, -z)
    }

    fun subtract(x: Vector3f): Vector3f {
        return this.add(-x.x, -x.y, -x.z)
    }

    fun multiply(number: Float): Vector3f {
        return Vector3f(this.x * number, this.y * number, this.z * number)
    }

    fun divide(number: Float): Vector3f {
        return Vector3f(this.x / number, this.y / number, this.z / number)
    }

    fun ceil(): Vector3f {
        return Vector3f(
            kotlin.math.ceil(x.toDouble()).toInt().toFloat(), kotlin.math.ceil(
                y.toDouble()
            ).toInt().toFloat(), kotlin.math.ceil(z.toDouble()).toInt().toFloat()
        )
    }

    fun floor(): Vector3f {
        return Vector3f(
            floorX.toFloat(),
            floorY.toFloat(),
            floorZ.toFloat()
        )
    }

    fun round(): Vector3f {
        return Vector3f(
            kotlin.math.round(this.x), kotlin.math.round(this.y), kotlin.math.round(
                this.z
            )
        )
    }

    fun abs(): Vector3f {
        return Vector3f(
            kotlin.math.abs(x.toDouble()).toInt().toFloat(), kotlin.math.abs(
                y.toDouble()
            ).toInt().toFloat(), kotlin.math.abs(z.toDouble()).toInt().toFloat()
        )
    }

    fun getSide(side: Int): Vector3f {
        return this.getSide(side, 1)
    }

    fun getSide(side: Int, step: Int): Vector3f {
        return when (side) {
            SIDE_DOWN -> Vector3f(
                this.x,
                y - step,
                z
            )

            SIDE_UP -> Vector3f(
                this.x,
                y + step,
                z
            )

            SIDE_NORTH -> Vector3f(
                this.x,
                y,
                z - step
            )

            SIDE_SOUTH -> Vector3f(
                this.x,
                y,
                z + step
            )

            SIDE_WEST -> Vector3f(
                this.x - step,
                y,
                z
            )

            SIDE_EAST -> Vector3f(
                this.x + step,
                y,
                z
            )

            else -> this
        }
    }

    fun distance(pos: Vector3f): Double {
        return sqrt(this.distanceSquared(pos))
    }

    fun distanceSquared(pos: Vector3f): Double {
        return (this.x - pos.x).toDouble().pow(2.0) + (this.y - pos.y).toDouble()
            .pow(2.0) + (this.z - pos.z).toDouble().pow(2.0)
    }

    fun maxPlainDistance(x: Float = 0f, z: Float = 0f): Float {
        return max(kotlin.math.abs((this.x - x).toDouble()), kotlin.math.abs((this.z - z).toDouble())).toFloat()
    }

    fun maxPlainDistance(vector: Vector2f): Float {
        return this.maxPlainDistance(vector.x, vector.y)
    }

    fun toHorizontal(): Vector2f {
        return Vector2f(this.x, this.z)
    }

    fun maxPlainDistance(x: Vector3f): Float {
        return this.maxPlainDistance(x.x, x.z)
    }

    /**
     * Calculates the Length of this Vector
     *
     * @return The Length of this Vector.
     */
    fun length(): Double {
        return sqrt(lengthSquared().toDouble())
    }

    fun lengthSquared(): Float {
        return this.x * this.x + this.y * this.y + this.z * this.z
    }

    fun normalize(): Vector3f {
        val len = this.lengthSquared()
        if (len > 0) {
            return this.divide(sqrt(len.toDouble()).toFloat())
        }
        return Vector3f(0f, 0f, 0f)
    }

    /**
     * Scalar Product of this Vector and the Vector supplied.
     *
     * @param v Vector to calculate the scalar product to.
     * @return Scalar Product
     */
    fun dot(v: Vector3f): Float {
        return this.x * v.x + this.y * v.y + this.z * v.z
    }

    /**
     * Calculates the cross product of this Vector and the given Vector
     *
     * @param v the vector to calculate the cross product with.
     * @return a Vector at right angle to this and other
     */
    fun cross(v: Vector3f): Vector3f {
        return Vector3f(
            this.y * v.z - this.z * v.y,
            this.z * v.x - this.x * v.z,
            this.x * v.y - this.y * v.x
        )
    }

    /* PowerNukkit: The Angle class was removed because it had all rights reserved copyright on it.
     * Calculates the angle between this and the supplied Vector.
     *
     * @param v the Vector to calculate the angle to.
     * @return the Angle between the two Vectors.
     */
    /*public Angle angleBetween(Vector3f v) {
        return Angle.fromRadian(Math.acos(Math.min(Math.max(this.normalize().dot(v.normalize()), -1.0f), 1.0f)));
    }*/
    /**
     * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     *
     * @param v vector
     * @param x x value
     * @return intermediate vector
     */
    fun getIntermediateWithXValue(v: Vector3f, x: Float): Vector3f? {
        val xDiff = v.x - this.x
        val yDiff = v.y - this.y
        val zDiff = v.z - this.z
        if (xDiff * xDiff < 0.0000001) {
            return null
        }
        val f = (x - this.x) / xDiff
        return if (f < 0 || f > 1) {
            null
        } else {
            Vector3f(
                this.x + xDiff * f,
                y + yDiff * f,
                z + zDiff * f
            )
        }
    }

    /**
     * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     *
     * @param v vector
     * @param y y value
     * @return intermediate vector
     */
    fun getIntermediateWithYValue(v: Vector3f, y: Float): Vector3f? {
        val xDiff = v.x - this.x
        val yDiff = v.y - this.y
        val zDiff = v.z - this.z
        if (yDiff * yDiff < 0.0000001) {
            return null
        }
        val f = (y - this.y) / yDiff
        return if (f < 0 || f > 1) {
            null
        } else {
            Vector3f(
                this.x + xDiff * f,
                y + yDiff * f,
                z + zDiff * f
            )
        }
    }

    /**
     * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     *
     * @param v vector
     * @param z z value
     * @return intermediate vector
     */
    fun getIntermediateWithZValue(v: Vector3f, z: Float): Vector3f? {
        val xDiff = v.x - this.x
        val yDiff = v.y - this.y
        val zDiff = v.z - this.z
        if (zDiff * zDiff < 0.0000001) {
            return null
        }
        val f = (z - this.z) / zDiff
        return if (f < 0 || f > 1) {
            null
        } else {
            Vector3f(
                this.x + xDiff * f,
                y + yDiff * f,
                this.z + zDiff * f
            )
        }
    }

    fun setComponents(x: Float, y: Float, z: Float): Vector3f {
        this.x = x
        this.y = y
        this.z = z
        return this
    }

    override fun toString(): String {
        return "Vector3(x=" + this.x + ",y=" + this.y + ",z=" + this.z + ")"
    }

    companion object : ProtoCodec<Vector3f> {
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

        override fun serialize(value: Vector3f, stream: Sink) {
            ProtoLE.Float.serialize(value.x, stream)
            ProtoLE.Float.serialize(value.y, stream)
            ProtoLE.Float.serialize(value.z, stream)
        }

        override fun deserialize(stream: Source): Vector3f {
            return Vector3f(
                x = ProtoLE.Float.deserialize(stream),
                y = ProtoLE.Float.deserialize(stream),
                z = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
