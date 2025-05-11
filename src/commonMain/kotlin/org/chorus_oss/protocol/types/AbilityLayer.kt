package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Short

data class AbilityLayer(
    var layerType: Type,
    var abilitiesSet: PlayerAbilitySet,
    var abilityValues: PlayerAbilitySet,
    var flySpeed: Float,
    var verticalFlySpeed: Float,
    var walkSpeed: Float,
) {
    companion object : ProtoCodec<AbilityLayer> {
        enum class Type {
            CACHE,
            BASE,
            SPECTATOR,
            COMMANDS,
            EDITOR,
            LOADING_SCREEN;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    ProtoLE.Short.serialize(value.ordinal.toShort(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[ProtoLE.Short.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: AbilityLayer, stream: Sink) {
            Type.serialize(value.layerType, stream)
            PlayerAbilitySet.serialize(value.abilitiesSet, stream)
            PlayerAbilitySet.serialize(value.abilityValues, stream)
            ProtoLE.Float.serialize(value.flySpeed, stream)
            ProtoLE.Float.serialize(value.verticalFlySpeed, stream)
            ProtoLE.Float.serialize(value.walkSpeed, stream)
        }

        override fun deserialize(stream: Source): AbilityLayer {
            return AbilityLayer(
                layerType = Type.deserialize(stream),
                abilitiesSet = PlayerAbilitySet.deserialize(stream),
                abilityValues = PlayerAbilitySet.deserialize(stream),
                flySpeed = ProtoLE.Float.deserialize(stream),
                verticalFlySpeed = ProtoLE.Float.deserialize(stream),
                walkSpeed = ProtoLE.Float.deserialize(stream)
            )
        }
    }
}
