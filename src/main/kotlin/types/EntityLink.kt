package org.chorus_oss.protocol.types

data class EntityLink(
    val fromEntityUniqueId: Long,
    val toEntityUniqueId: Long,
    val type: Type,
    val immediate: Boolean,
    val riderInitiated: Boolean,
    val vehicleAngularVelocity: Float
) {
    @Deprecated("")
    constructor(
        fromEntityUniqueId: Long,
        toEntityUniqueId: Long,
        type: Type,
        immediate: Boolean,
        riderInitiated: Boolean
    ) : this(fromEntityUniqueId, toEntityUniqueId, type, immediate, riderInitiated, 0f)

    enum class Type {
        REMOVE,
        RIDER,
        PASSENGER
    }
}
