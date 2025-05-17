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
    BYTE,
    SHORT,
    INT,
    FLOAT,
    STRING,
    NBT,
    VECTOR3I,
    LONG,
    VECTOR3F;

    companion object : ProtoCodec<ActorDataFormat> {
        override fun serialize(value: ActorDataFormat, stream: Sink) {
            ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
        }

        override fun deserialize(stream: Source): ActorDataFormat {
            return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
        }

        fun from(value: Any): ActorDataFormat {
            return when (value) {
                is Byte -> BYTE
                is Short -> SHORT
                is Int -> INT
                is Float -> FLOAT
                is Long -> LONG
                is String -> STRING
                is CompoundTag -> NBT
                is IVector3 -> VECTOR3I
                is Vector3f -> VECTOR3F

                else -> throw IllegalArgumentException("Unsupported type ${value::class}")
            }
        }
    }
}