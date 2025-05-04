package org.chorus_oss.protocol.types.inventory

import org.chorus_oss.protocol.types.itemstack.ContainerSlotType

data class FullContainerName(
    val container: ContainerSlotType,
    val dynamicId: Int? = null
)
