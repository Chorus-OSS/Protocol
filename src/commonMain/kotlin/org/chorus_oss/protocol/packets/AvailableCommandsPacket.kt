package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
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
) : Packet(id) {
    companion object : PacketCodec<AvailableCommandsPacket> {
        override val id: Int
            get() = ProtocolInfo.AVAILABLE_COMMANDS_PACKET

        override fun deserialize(stream: Source): AvailableCommandsPacket {
            val enumValuesLen: Int
            return AvailableCommandsPacket(
                enumValues = ProtoHelper.deserializeList(stream, Proto.String).also { enumValuesLen = it.size },
                chainedSubcommandValues = ProtoHelper.deserializeList(stream, Proto.String),
                suffixes = ProtoHelper.deserializeList(stream, Proto.String),
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
                chainedSubcommands = ProtoHelper.deserializeList(stream, ChainedSubcommand),
                commands = ProtoHelper.deserializeList(stream, Command),
                dynamicEnums = ProtoHelper.deserializeList(stream, CommandDynamicEnum),
                constraints = ProtoHelper.deserializeList(stream, CommandEnumConstraint)
            )
        }

        override fun serialize(value: AvailableCommandsPacket, stream: Sink) {
            ProtoHelper.serializeList(value.enumValues, stream, Proto.String)
            ProtoHelper.serializeList(value.chainedSubcommandValues, stream, Proto.String)
            ProtoHelper.serializeList(value.suffixes, stream, Proto.String)

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

            ProtoHelper.serializeList(value.chainedSubcommands, stream, ChainedSubcommand)
            ProtoHelper.serializeList(value.commands, stream, Command)
            ProtoHelper.serializeList(value.dynamicEnums, stream, CommandDynamicEnum)
            ProtoHelper.serializeList(value.constraints, stream, CommandEnumConstraint)
        }
    }
}
