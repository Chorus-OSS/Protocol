package org.chorus_oss.protocol.types.itemstack.request

import org.chorus_oss.protocol.types.inventory.FullContainerName
import org.chorus_oss.protocol.types.itemstack.ContainerSlotType

/**
 * Holds information on a specific slot client-side.
 */
data class ItemStackRequestSlotData(
    /**
     * container that the slots that follow are in.
     *
     */
    @Deprecated("since v712 - FullContainerName#getContainer should be preferred")
    val container: ContainerSlotType,

    /**
     * slot is the index of the slot within the container
     */
    val slot: Int,

    /**
     * stackNetworkId is the unique stack ID that the client assumes to be present in this slot. The server
     * must check if these IDs match. If they do not match, servers should reject the stack request that the
     * action holding this info was in.
     */
    val stackNetworkId: Int,
    val containerName: FullContainerName,
)
