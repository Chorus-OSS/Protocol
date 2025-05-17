package org.chorus_oss.protocol.types.command

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.UInt

data class CommandEnumConstraint(
    val enumValueIndex: UInt,
    val enumIndex: UInt,
    val constraints: List<Type>
) {
    companion object : ProtoCodec<CommandEnumConstraint> {
        enum class Type {
            CheatsEnabled,
            OperatorPermissions,
            HostPermissions;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: CommandEnumConstraint, stream: Sink) {
            ProtoLE.UInt.serialize(value.enumValueIndex, stream)
            ProtoLE.UInt.serialize(value.enumIndex, stream)
            ProtoHelper.serializeList(value.constraints, stream, Type)
        }

        override fun deserialize(stream: Source): CommandEnumConstraint {
            return CommandEnumConstraint(
                enumValueIndex = ProtoLE.UInt.deserialize(stream),
                enumIndex = ProtoLE.UInt.deserialize(stream),
                constraints = ProtoHelper.deserializeList(stream, Type)
            )
        }
    }
}
