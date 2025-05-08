package org.chorus_oss.protocol.types

import java.util.*

class AbilityLayer {
    var layerType: Type = Type.CACHE
    var abilitiesSet: MutableSet<PlayerAbility> = EnumSet.noneOf(PlayerAbility::class.java)
    var abilityValues: MutableSet<PlayerAbility> = EnumSet.noneOf(PlayerAbility::class.java)
    var flySpeed = 0f
    var verticalFlySpeed = 0f
    var walkSpeed = 0f

    enum class Type {
        CACHE,
        BASE,
        SPECTATOR,
        COMMANDS,
        EDITOR,
        LOADING_SCREEN
    }
}
