package org.chorus_oss.protocol.types.itemstack.response

import org.chorus_oss.protocol.types.inventory.FullContainerName
import org.chorus_oss.protocol.types.itemstack.ContainerSlotType

/**
 * ContainerEntry holds information on what slots in a container have what item stack in them.
 */
data class ItemStackResponseContainer(
    /**
     * container that the slots that follow are in.
     *
     */
    @Deprecated("since v712 - FullContainerName#getContainer should be preferred")
    val container: ContainerSlotType,

    /**
     * items holds information on what item stack should be present in specific slots in the container.
     */
    val items: MutableList<ItemStackResponseSlot>,
    val containerName: FullContainerName,
)
