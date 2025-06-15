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
    None,
    Attack,
    Collect,
    Destroy,
    DetectRedstone,
    DetectObstacle,
    Drop,
    DropAll,

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
    Inspect,

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
    InspectData,

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
    InspectItemCount,

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
    InspectItemDetail,

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
    InspectItemSpace,
    Interact,

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
    Move,
    PlaceBlock,
    Till,
    TransferItemTo,
    Turn
}
