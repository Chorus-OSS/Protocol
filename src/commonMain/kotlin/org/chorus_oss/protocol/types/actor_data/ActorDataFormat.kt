package org.chorus_oss.protocol.types.actor_data

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.Vector3f

enum class ActorDataFormat {
    Byte,
    Short,
    Int,
    Float,
    String,
    Nbt,
    Vector3I,
    Long,
    Vector3F;

    companion object : ProtoCodec<ActorDataFormat> {
        override fun serialize(value: ActorDataFormat, stream: Sink) {
            ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
        }

        override fun deserialize(stream: Source): ActorDataFormat {
            return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
        }

        fun from(value: Any): ActorDataFormat {
            return when (value) {
                is Byte -> Byte
                is Short -> Short
                is Int -> Int
                is Float -> Float
                is Long -> Long
                is String -> String
                is CompoundTag -> Nbt
                is IVector3 -> Vector3I
                is Vector3f -> Vector3F

                else -> throw IllegalArgumentException("Unsupported type ${value::class}")
            }
        }
    }
}