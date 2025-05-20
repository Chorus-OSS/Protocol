package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int

data class PlayerMovementSettings(
    val movementMode: PlayerMovementMode,
    val rewindHistorySize: Int,
    val serverAuthoritativeBlockBreaking: Boolean,
) {
    companion object : ProtoCodec<PlayerMovementSettings> {
        override fun serialize(value: PlayerMovementSettings, stream: Sink) {
            PlayerMovementMode.serialize(value.movementMode, stream)
            ProtoVAR.Int.serialize(value.rewindHistorySize, stream)
            Proto.Boolean.serialize(value.serverAuthoritativeBlockBreaking, stream)
        }

        override fun deserialize(stream: Source): PlayerMovementSettings {
            return PlayerMovementSettings(
                movementMode = PlayerMovementMode.deserialize(stream),
                rewindHistorySize = ProtoVAR.Int.deserialize(stream),
                serverAuthoritativeBlockBreaking = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
