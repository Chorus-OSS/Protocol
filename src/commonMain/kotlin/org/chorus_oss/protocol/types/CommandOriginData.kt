package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.Uuid
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class CommandOriginData(
    val commandType: Origin,
    val commandUUID: Uuid,
    val requestId: String, // event
    val commandData: CommandData?
) {
    companion object : ProtoCodec<CommandOriginData> {
        enum class Origin {
            Player,
            Block,
            MinecartBlock,
            DevConsole,
            Test,
            AutomationPlayer,
            ClientAutomation,
            DedicatedServer,
            Entity,
            Virtual,
            GameArgument,
            EntityServer,
            Precompiled,
            GameDirectorEntityServer,
            Script,
            ExecuteContext;

            companion object : ProtoCodec<Origin> {
                override fun serialize(
                    value: Origin,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): Origin {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }
            }
        }

        interface CommandData

        data class PlayerIDData(
            val playerUniqueID: ActorUniqueID,
        ) : CommandData {
            companion object : ProtoCodec<PlayerIDData> {
                override fun serialize(
                    value: PlayerIDData,
                    stream: Sink
                ) {
                    ActorUniqueID.serialize(value.playerUniqueID, stream)
                }

                override fun deserialize(stream: Source): PlayerIDData {
                    return PlayerIDData(
                        playerUniqueID = ActorUniqueID.deserialize(stream),
                    )
                }
            }
        }

        override fun serialize(value: CommandOriginData, stream: Sink) {
            Origin.serialize(value.commandType, stream)
            Proto.Uuid.serialize(value.commandUUID, stream)
            Proto.String.serialize(value.requestId, stream)
            when (value.commandType) {
                Origin.Test,
                Origin.DevConsole -> (value.commandData as PlayerIDData).let { PlayerIDData.serialize(it, stream) }

                else -> Unit
            }
        }

        override fun deserialize(stream: Source): CommandOriginData {
            val type: Origin
            return CommandOriginData(
                commandType = Origin.deserialize(stream).also { type = it },
                commandUUID = Proto.Uuid.deserialize(stream),
                requestId = Proto.String.deserialize(stream),
                commandData = when (type) {
                    Origin.Test,
                    Origin.DevConsole -> PlayerIDData.deserialize(stream)

                    else -> null
                }
            )
        }
    }
}
