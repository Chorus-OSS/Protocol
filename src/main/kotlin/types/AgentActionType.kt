package org.chorus_oss.protocol.types

/**
 * @since v503
 *
 *
 * Enum members without a JSON example use the following format:
 * ```
 * {
 *     "result": true | false
 * }
 * ```
 */
enum class AgentActionType {
    NONE,
    ATTACK,
    COLLECT,
    DESTROY,
    DETECT_REDSTONE,
    DETECT_OBSTACLE,
    DROP,
    DROP_ALL,

    /**
     * JSON Data:
     * ```
     * {
     *     "result": {
     *         "block": {
     *             "id": "dirt",
     *             "namespace": "minecraft",
     *             "aux": 0
     *         }
     *     }
     * }
     * ```
     */
    INSPECT,

    /**
     * JSON Data:
     * ```
     * {
     *     "result": {
     *         "block": {
     *             "aux": 0
     *         }
     *     }
     * }
     * ```
     */
    INSPECT_DATA,

    /**
     * JSON Data:
     * ```
     * {
     *     "result": {
     *         "item": {
     *             "count": 10
     *         }
     *     }
     * }
     * ```
     */
    INSPECT_ITEM_COUNT,

    /**
     * **Note:** If the enchantment level is above 10, the i18n string should not be used.
     *
     *
     * JSON Data:
     * ```
     * {
     *     "result": {
     *         "item": {
     *             "id": "dirt",
     *             "namespace": "minecraft",
     *             "aux": 0,
     *             "maxStackSize": 64,
     *             "stackSize": 1,
     *             "freeStackSize": 63,
     *             "enchantments": [
     *                 {
     *                     "name": "enchantment.knockback enchantment.level.1" | "enchantment.knockback 20",
     *                     "type": 1,
     *                     "level": 1
     *                 }
     *             ]
     *         }
     *     }
     * }
     * ```
     */
    INSPECT_ITEM_DETAIL,

    /**
     * JSON Data:
     * ```
     * {
     *     "result": {
     *         "item": {
     *             "maxStackSize": 64,
     *             "stackSize": 1,
     *             "freeStackSize": 63 // (maxStackSize - stackSize)
     *         }
     *     }
     * }
     * ```
     */
    INSPECT_ITEM_SPACE,
    INTERACT,

    /**
     * JSON Data:
     * ```
     * {
     *     "status": {
     *         "statusName": "moving" | "blocked" | "reached"
     *     }
     * }
     * ```
     */
    MOVE,
    PLACE_BLOCK,
    TILL,
    TRANSFER_ITEM_TO,
    TURN
}
