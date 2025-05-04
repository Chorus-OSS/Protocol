package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3

import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.CommandBlockMode

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
) : DataPacket(), PacketEncoder {
    interface CommandBlockHolderData

    data class CommandBlockActorData(
        val targetRuntimeID: ActorRuntimeID
    ) : CommandBlockHolderData

    data class CommandBlockData(
        val blockPosition: BlockVector3,
        val commandBlockMode: CommandBlockMode,
        val redstoneMode: Boolean,
        val isConditional: Boolean,
    ) : CommandBlockHolderData

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.isBlock)
        when (this.isBlock) {
            true -> {
                val commandBlockData = this.commandBlockHolderData as CommandBlockData

                byteBuf.writeBlockVector3(commandBlockData.blockPosition)
                byteBuf.writeUnsignedVarInt(commandBlockData.commandBlockMode.ordinal)
                byteBuf.writeBoolean(commandBlockData.redstoneMode)
                byteBuf.writeBoolean(commandBlockData.isConditional)
            }

            false -> {
                val commandBlockActorData = this.commandBlockHolderData as CommandBlockActorData

                byteBuf.writeActorRuntimeID(commandBlockActorData.targetRuntimeID)
            }
        }
        byteBuf.writeString(this.command)
        byteBuf.writeString(this.lastOutput)
        byteBuf.writeString(this.name)
        byteBuf.writeString(this.filteredName)
        byteBuf.writeBoolean(this.trackOutput)
        byteBuf.writeIntLE(this.tickDelay)
        byteBuf.writeBoolean(this.shouldExecuteOnFirstTick)
    }

    override fun pid(): Int {
        return ProtocolInfo.COMMAND_BLOCK_UPDATE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<CommandBlockUpdatePacket> {
        override fun decode(byteBuf: ByteBuf): CommandBlockUpdatePacket {
            val isBlock: Boolean
            return CommandBlockUpdatePacket(
                isBlock = byteBuf.readBoolean().also { isBlock = it },
                commandBlockHolderData = when (isBlock) {
                    true -> CommandBlockData(
                        blockPosition = byteBuf.readBlockVector3(),
                        commandBlockMode = CommandBlockMode.entries[byteBuf.readUnsignedVarInt()],
                        redstoneMode = byteBuf.readBoolean(),
                        isConditional = byteBuf.readBoolean(),
                    )

                    false -> CommandBlockActorData(
                        targetRuntimeID = byteBuf.readActorRuntimeID(),
                    )
                },
                command = byteBuf.readString(),
                lastOutput = byteBuf.readString(),
                name = byteBuf.readString(),
                filteredName = byteBuf.readString(),
                trackOutput = byteBuf.readBoolean(),
                tickDelay = byteBuf.readIntLE(),
                shouldExecuteOnFirstTick = byteBuf.readBoolean()
            )
        }
    }
}
