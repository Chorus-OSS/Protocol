package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class PlayerBlockActionData(
    val action: PlayerActionType,
    val position: BlockPos?,
    val facing: Int?,
) {
    companion object : ProtoCodec<PlayerBlockActionData> {
        override fun serialize(value: PlayerBlockActionData, stream: Sink) {
            PlayerActionType.serialize(value.action, stream)
            when (value.action) {
                PlayerActionType.StartDestroyBlock,
                PlayerActionType.AbortDestroyBlock,
                PlayerActionType.CrackBlock,
                PlayerActionType.PredictDestroyBlock,
                PlayerActionType.ContinueDestroyBlock -> BlockPos.serialize(value.position as BlockPos, stream)

                else -> Unit
            }
            when (value.action) {
                PlayerActionType.StartDestroyBlock,
                PlayerActionType.AbortDestroyBlock,
                PlayerActionType.CrackBlock,
                PlayerActionType.PredictDestroyBlock,
                PlayerActionType.ContinueDestroyBlock -> ProtoVAR.Int.serialize(value.facing as Int, stream)

                else -> Unit
            }
        }

        override fun deserialize(stream: Source): PlayerBlockActionData {
            val action: PlayerActionType
            return PlayerBlockActionData(
                action = PlayerActionType.deserialize(stream).also { action = it },
                position = when (action) {
                    PlayerActionType.StartDestroyBlock,
                    PlayerActionType.AbortDestroyBlock,
                    PlayerActionType.CrackBlock,
                    PlayerActionType.PredictDestroyBlock,
                    PlayerActionType.ContinueDestroyBlock -> BlockPos.deserialize(stream)

                    else -> null
                },
                facing = when (action) {
                    PlayerActionType.StartDestroyBlock,
                    PlayerActionType.AbortDestroyBlock,
                    PlayerActionType.CrackBlock,
                    PlayerActionType.PredictDestroyBlock,
                    PlayerActionType.ContinueDestroyBlock -> ProtoVAR.Int.deserialize(stream)

                    else -> null
                }
            )
        }
    }
}