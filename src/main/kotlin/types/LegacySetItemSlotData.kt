package org.chorus_oss.protocol.types


data class LegacySetItemSlotData(
    val containerId: Int,
    val slots: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LegacySetItemSlotData

        if (containerId != other.containerId) return false
        if (!slots.contentEquals(other.slots)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerId
        result = 31 * result + slots.contentHashCode()
        return result
    }
}
