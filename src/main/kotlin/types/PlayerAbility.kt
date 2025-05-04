package org.chorus_oss.protocol.types

enum class PlayerAbility(
    // Feature bits for RequestPermissionsPacket
    val bit: Int = 0
) {
    BUILD(0x1),
    MINE(0x2),
    DOORS_AND_SWITCHES(0x4),
    OPEN_CONTAINERS(0x8),
    ATTACK_PLAYERS(0x10),
    ATTACK_MOBS(0x20),
    OPERATOR_COMMANDS(0x40),
    TELEPORT(0x80),

    INVULNERABLE,
    FLYING,
    MAY_FLY,
    INSTABUILD,
    LIGHTNING,
    FLY_SPEED,
    WALK_SPEED,
    MUTED,
    WORLD_BUILDER,
    NO_CLIP,
    PRIVILEGED_BUILDER,
    VERTICAL_FLY_SPEED;

    companion object {
        @JvmField
        val VALUES: List<PlayerAbility> = entries.toList()
    }
}
