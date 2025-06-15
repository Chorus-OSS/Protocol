package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.IVector3

data class GameTestRequestPacket(
    val maxTestsPerBatch: Int,
    val repetitions: Int,
    val rotation: Rotation,
    val stopOnError: Boolean,
    val position: IVector3,
    val testsPerRow: Int,
    val name: String,
) : Packet(id) {
    companion object : PacketCodec<GameTestRequestPacket> {
        enum class Rotation {
            Rotate0,
            Rotate90,
            Rotate180,
            Rotate270,
            Rotate360;

            companion object : ProtoCodec<Rotation> {
                override fun serialize(
                    value: Rotation,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Rotation {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 194

        override fun serialize(value: GameTestRequestPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.maxTestsPerBatch, stream)
            ProtoVAR.Int.serialize(value.repetitions, stream)
            Rotation.serialize(value.rotation, stream)
            Proto.Boolean.serialize(value.stopOnError, stream)
            IVector3.serialize(value.position, stream)
            ProtoVAR.Int.serialize(value.testsPerRow, stream)
            Proto.String.serialize(value.name, stream)
        }

        override fun deserialize(stream: Source): GameTestRequestPacket {
            return GameTestRequestPacket(
                maxTestsPerBatch = ProtoVAR.Int.deserialize(stream),
                repetitions = ProtoVAR.Int.deserialize(stream),
                rotation = Rotation.deserialize(stream),
                stopOnError = Proto.Boolean.deserialize(stream),
                position = IVector3.deserialize(stream),
                testsPerRow = ProtoVAR.Int.deserialize(stream),
                name = Proto.String.deserialize(stream),
            )
        }
    }
}
