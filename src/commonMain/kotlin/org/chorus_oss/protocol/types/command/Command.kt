package org.chorus_oss.protocol.types.command

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.types.CommandPermission

data class Command(
    val name: String,
    val description: String,
    val flags: UShort,
    val permissionLevel: CommandPermission,
    val aliasesOffset: UInt,
    val chainedSubcommandOffsets: List<UShort>,
    val overloads: List<CommandOverload>
) {
    companion object : ProtoCodec<Command> {
        override fun serialize(value: Command, stream: Buffer) {
            Proto.String.serialize(value.name, stream)
            Proto.String.serialize(value.description, stream)
            ProtoLE.UShort.serialize(value.flags, stream)
            Proto.Byte.serialize(value.permissionLevel.ordinal.toByte(), stream)
            ProtoLE.UInt.serialize(value.aliasesOffset, stream)
            ProtoHelper.serializeList(value.chainedSubcommandOffsets, stream, ProtoLE.UShort::serialize)
            ProtoHelper.serializeList(value.overloads, stream, CommandOverload::serialize)
        }

        override fun deserialize(stream: Buffer): Command {
            return Command(
                name = Proto.String.deserialize(stream),
                description = Proto.String.deserialize(stream),
                flags = ProtoLE.UShort.deserialize(stream),
                permissionLevel = CommandPermission.entries[Proto.Byte.deserialize(stream).toInt()],
                aliasesOffset = ProtoLE.UInt.deserialize(stream),
                chainedSubcommandOffsets = ProtoHelper.deserializeList(stream, ProtoLE.UShort::deserialize),
                overloads = ProtoHelper.deserializeList(stream, CommandOverload::deserialize)
            )
        }
    }
}
