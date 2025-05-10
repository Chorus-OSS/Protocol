package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UByte
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.types.command.*

data class AvailableCommandsPacket(
    val enumValues: List<String>,
    val chainedSubcommandValues: List<String>,
    val suffixes: List<String>,
    val enums: List<CommandEnum>,
    val chainedSubcommands: List<ChainedSubcommand>,
    val commands: List<Command>,
    val dynamicEnums: List<CommandDynamicEnum>,
    val constraints: List<CommandEnumConstraint>
) {
    companion object : PacketCodec<AvailableCommandsPacket> {
        override val id: Int
            get() = ProtocolInfo.AVAILABLE_COMMANDS_PACKET

        override fun deserialize(stream: Buffer): AvailableCommandsPacket {
            val enumValuesLen: Int
            return AvailableCommandsPacket(
                enumValues = ProtoHelper.deserializeList(stream, Proto.String::deserialize).also { enumValuesLen = it.size },
                chainedSubcommandValues = ProtoHelper.deserializeList(stream, Proto.String::deserialize),
                suffixes = ProtoHelper.deserializeList(stream, Proto.String::deserialize),
                enums = ProtoHelper.deserializeList(stream) { buf ->
                    CommandEnum(
                        type = Proto.String.deserialize(buf),
                        valueIndices = ProtoHelper.deserializeList(buf) { buf2 ->
                            when {
                                enumValuesLen <= UByte.MAX_VALUE.toInt() -> {
                                    Proto.UByte.deserialize(buf2).toUInt()
                                }

                                enumValuesLen <= UShort.MAX_VALUE.toInt() -> {
                                    ProtoLE.UShort.deserialize(buf2).toUInt()
                                }

                                else -> {
                                    ProtoLE.UInt.deserialize(buf2)
                                }
                            }
                        }
                    )
                },
                chainedSubcommands = ProtoHelper.deserializeList(stream, ChainedSubcommand::deserialize),
                commands = ProtoHelper.deserializeList(stream, Command::deserialize),
                dynamicEnums = ProtoHelper.deserializeList(stream, CommandDynamicEnum::deserialize),
                constraints = ProtoHelper.deserializeList(stream, CommandEnumConstraint::deserialize)
            )
        }

        override fun serialize(value: AvailableCommandsPacket, stream: Buffer) {
            ProtoHelper.serializeList(value.enumValues, stream, Proto.String::serialize)
            ProtoHelper.serializeList(value.chainedSubcommandValues, stream, Proto.String::serialize)
            ProtoHelper.serializeList(value.suffixes, stream, Proto.String::serialize)

            val enumValuesLen = value.enumValues.size
            ProtoHelper.serializeList(value.enums, stream) { enum, buf ->
                Proto.String.serialize(enum.type, buf)
                ProtoHelper.serializeList(enum.valueIndices, buf) { i, buf2 ->
                    when {
                        enumValuesLen <= UByte.MAX_VALUE.toInt() -> {
                            Proto.UByte.serialize(i.toUByte(), buf2)
                        }

                        enumValuesLen <= UShort.MAX_VALUE.toInt() -> {
                            ProtoLE.UShort.serialize(i.toUShort(), buf2)
                        }

                        else -> {
                            ProtoLE.UInt.serialize(i, buf2)
                        }
                    }
                }
            }

            ProtoHelper.serializeList(value.chainedSubcommands, stream, ChainedSubcommand::serialize)
            ProtoHelper.serializeList(value.commands, stream, Command::serialize)
            ProtoHelper.serializeList(value.dynamicEnums, stream, CommandDynamicEnum::serialize)
            ProtoHelper.serializeList(value.constraints, stream, CommandEnumConstraint::serialize)
        }
    }
}
