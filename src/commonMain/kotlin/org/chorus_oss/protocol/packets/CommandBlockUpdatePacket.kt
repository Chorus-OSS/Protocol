package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.CommandBlockMode
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3

data class CommandBlockUpdatePacket(
    val isBlock: Boolean,
    val commandBlockHolderData: CommandBlockHolderData,
    val command: String,
    val lastOutput: String,
    val name: String,
    val filteredName: String,
    val trackOutput: Boolean,
    val tickDelay: Int,
    val shouldExecuteOnFirstTick: Boolean,
) : Packet(id) {
    companion object : PacketCodec<CommandBlockUpdatePacket> {


        interface CommandBlockHolderData

        data class CommandBlockActorData(
            val targetRuntimeID: ActorRuntimeID
        ) : CommandBlockHolderData {
            companion object : ProtoCodec<CommandBlockActorData> {
                override fun serialize(
                    value: CommandBlockActorData,
                    stream: Sink
                ) {
                    ActorRuntimeID.serialize(value.targetRuntimeID, stream)
                }

                override fun deserialize(stream: Source): CommandBlockActorData {
                    return CommandBlockActorData(
                        targetRuntimeID = ActorRuntimeID.deserialize(stream),
                    )
                }
            }
        }

        data class CommandBlockData(
            val blockPosition: IVector3,
            val commandBlockMode: CommandBlockMode,
            val redstoneMode: Boolean,
            val isConditional: Boolean,
        ) : CommandBlockHolderData {
            companion object : ProtoCodec<CommandBlockData> {
                override fun serialize(
                    value: CommandBlockData,
                    stream: Sink
                ) {
                    UIVector3.serialize(value.blockPosition, stream)
                    CommandBlockMode.serialize(value.commandBlockMode, stream)
                    Proto.Boolean.serialize(value.redstoneMode, stream)
                    Proto.Boolean.serialize(value.isConditional, stream)
                }

                override fun deserialize(stream: Source): CommandBlockData {
                    return CommandBlockData(
                        blockPosition = UIVector3.deserialize(stream),
                        commandBlockMode = CommandBlockMode.deserialize(stream),
                        redstoneMode = Proto.Boolean.deserialize(stream),
                        isConditional = Proto.Boolean.deserialize(stream),
                    )
                }
            }
        }

        override val id: Int = 78

        override fun deserialize(stream: Source): CommandBlockUpdatePacket {
            val isBlock: Boolean
            return CommandBlockUpdatePacket(
                isBlock = Proto.Boolean.deserialize(stream).also { isBlock = it },
                commandBlockHolderData = when (isBlock) {
                    true -> CommandBlockData.deserialize(stream)
                    false -> CommandBlockActorData.deserialize(stream)
                },
                command = Proto.String.deserialize(stream),
                lastOutput = Proto.String.deserialize(stream),
                name = Proto.String.deserialize(stream),
                filteredName = Proto.String.deserialize(stream),
                trackOutput = Proto.Boolean.deserialize(stream),
                tickDelay = ProtoLE.Int.deserialize(stream),
                shouldExecuteOnFirstTick = Proto.Boolean.deserialize(stream)
            )
        }

        override fun serialize(
            value: CommandBlockUpdatePacket,
            stream: Sink
        ) {
            Proto.Boolean.serialize(value.isBlock, stream)
            when (value.isBlock) {
                true -> (value.commandBlockHolderData as CommandBlockData).let {
                    CommandBlockData.serialize(
                        it,
                        stream
                    )
                }

                false -> (value.commandBlockHolderData as CommandBlockActorData).let {
                    CommandBlockActorData.serialize(
                        it,
                        stream
                    )
                }
            }
            Proto.String.serialize(value.command, stream)
            Proto.String.serialize(value.lastOutput, stream)
            Proto.String.serialize(value.name, stream)
            Proto.String.serialize(value.filteredName, stream)
            Proto.Boolean.serialize(value.trackOutput, stream)
            ProtoLE.Int.serialize(value.tickDelay, stream)
            Proto.Boolean.serialize(value.shouldExecuteOnFirstTick, stream)
        }
    }
}
