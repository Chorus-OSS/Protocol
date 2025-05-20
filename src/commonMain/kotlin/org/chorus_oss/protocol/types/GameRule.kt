package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt

data class GameRule<T>(
    val name: String,
    val canBeModifiedByPlayer: Boolean,
    val value: T,
) {
    companion object : ProtoCodec<GameRule<*>> {
        override fun serialize(value: GameRule<*>, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            Proto.Boolean.serialize(value.canBeModifiedByPlayer, stream)

            when (value.value) {
                is Boolean -> {
                    ProtoVAR.UInt.serialize(1u, stream)
                    Proto.Boolean.serialize(value.value, stream)
                }

                is UInt -> {
                    ProtoVAR.UInt.serialize(2u, stream)
                    ProtoVAR.UInt.serialize(value.value, stream)
                }

                is Float -> {
                    ProtoVAR.UInt.serialize(3u, stream)
                    ProtoLE.Float.serialize(value.value, stream)
                }

                else -> throw IllegalArgumentException("Unsupported GameRule value type: ${value.value::class.simpleName}")
            }
        }

        override fun deserialize(stream: Source): GameRule<*> {
            return GameRule<Any>(
                name = Proto.String.deserialize(stream),
                canBeModifiedByPlayer = Proto.Boolean.deserialize(stream),
                value = ProtoVAR.UInt.deserialize(stream).let {
                    when (it) {
                        1u -> Proto.Boolean.deserialize(stream)
                        2u -> ProtoVAR.UInt.deserialize(stream)
                        3u -> ProtoLE.Float.deserialize(stream)
                        else -> throw IllegalArgumentException("Unknown GameRule type: $it")
                    }
                },
            )
        }
    }
}
