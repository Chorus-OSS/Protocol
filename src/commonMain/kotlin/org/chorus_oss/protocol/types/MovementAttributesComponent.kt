package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float

data class MovementAttributesComponent(
    val movementSpeed: Float,
    val underwaterMovementSpeed: Float,
    val lavaMovementSpeed: Float,
    val jumpStrength: Float,
    val health: Float,
    val hunger: Float
) {
    companion object : ProtoCodec<MovementAttributesComponent> {
        override fun serialize(value: MovementAttributesComponent, stream: Sink) {
            ProtoLE.Float.serialize(value.movementSpeed, stream)
            ProtoLE.Float.serialize(value.underwaterMovementSpeed, stream)
            ProtoLE.Float.serialize(value.lavaMovementSpeed, stream)
            ProtoLE.Float.serialize(value.jumpStrength, stream)
            ProtoLE.Float.serialize(value.health, stream)
            ProtoLE.Float.serialize(value.hunger, stream)
        }

        override fun deserialize(stream: Source): MovementAttributesComponent {
            return MovementAttributesComponent(
                movementSpeed = ProtoLE.Float.deserialize(stream),
                underwaterMovementSpeed = ProtoLE.Float.deserialize(stream),
                lavaMovementSpeed = ProtoLE.Float.deserialize(stream),
                jumpStrength = ProtoLE.Float.deserialize(stream),
                health = ProtoLE.Float.deserialize(stream),
                hunger = ProtoLE.Float.deserialize(stream)
            )
        }
    }
}
