package org.chorus_oss.protocol.types

enum class PlayerActionType {
    START_DESTROY_BLOCK,
    ABORT_DESTROY_BLOCK,
    STOP_DESTROY_BLOCK,
    GET_UPDATED_BLOCK,
    DROP_ITEM,
    START_SLEEPING,
    STOP_SLEEPING,
    RESPAWN,
    START_JUMP,
    START_SPRINTING,
    STOP_SPRINTING,
    START_SNEAKING,
    STOP_SNEAKING,
    CREATIVE_DESTROY_BLOCK,
    CHANGE_DIMENSION_ACK,
    START_GLIDING,
    STOP_GLIDING,
    DENY_DESTROY_BLOCK,
    CRACK_BLOCK,
    CHANGE_SKIN,
    UPDATED_ENCHANTING_SEED,
    START_SWIMMING,
    STOP_SWIMMING,
    START_SPIN_ATTACK,
    STOP_SPIN_ATTACK,
    INTERACT_WITH_BLOCK,
    PREDICT_DESTROY_BLOCK,
    CONTINUE_DESTROY_BLOCK,
    START_ITEM_USE_ON,
    STOP_ITEM_USE_ON,
    HANDLED_TELEPORT,

    /**
     * @since v594
     */
    MISSED_SWING,

    /**
     * @since v594
     */
    START_CRAWLING,

    /**
     * @since v594
     */
    STOP_CRAWLING,

    /**
     * @since v618
     */
    START_FLYING,

    /**
     * @since v618
     */
    STOP_FLYING,

    /**
     * @since v622
     */
    RECEIVED_SERVER_DATA,

    /**
     * @since v766
     */
    START_ITEM_USE;

    companion object {
        private val VALUES = entries.toTypedArray()

        fun from(id: Int): PlayerActionType {
            return VALUES[id]
        }

        fun fromOrNull(id: Int): PlayerActionType? {
            return VALUES.getOrNull(id)
        }
    }
}
