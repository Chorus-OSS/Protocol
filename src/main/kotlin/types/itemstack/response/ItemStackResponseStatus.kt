package org.chorus_oss.protocol.types.itemstack.response

/**
 * Represents the status of a [ItemStackResponse].
 */
enum class ItemStackResponseStatus {
    /**
     * The transaction request was valid.
     */
    OK,

    /**
     * The transaction request was invalid.
     */
    ERROR
}
