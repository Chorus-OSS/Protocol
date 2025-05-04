package org.chorus_oss.protocol.types

@JvmRecord
data class PropertySyncData(
    val intProperties: IntArray,
    val floatProperties: FloatArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PropertySyncData

        if (!intProperties.contentEquals(other.intProperties)) return false
        if (!floatProperties.contentEquals(other.floatProperties)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = intProperties.contentHashCode()
        result = 31 * result + floatProperties.contentHashCode()
        return result
    }
}
