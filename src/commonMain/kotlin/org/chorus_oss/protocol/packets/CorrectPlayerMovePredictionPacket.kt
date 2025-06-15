package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.Vector2f
import org.chorus_oss.protocol.types.Vector3f

data class CorrectPlayerMovePredictionPacket(
    val predictionType: PredictionType,
    val position: Vector3f,
    val delta: Vector3f,
    val rotation: Vector2f?,
    val vehicleAngularVelocity: Float?,
    val onGround: Boolean,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<CorrectPlayerMovePredictionPacket> {
        enum class PredictionType {
            Player,
            Vehicle;

            companion object : ProtoCodec<PredictionType> {
                override fun serialize(
                    value: PredictionType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): PredictionType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 161

        override fun serialize(
            value: CorrectPlayerMovePredictionPacket,
            stream: Sink
        ) {
            PredictionType.serialize(value.predictionType, stream)
            Vector3f.serialize(value.position, stream)
            Vector3f.serialize(value.delta, stream)
            when (value.predictionType) {
                PredictionType.Vehicle -> Vector2f.serialize(value.rotation as Vector2f, stream)
                else -> Unit
            }
            when (value.predictionType) {
                PredictionType.Vehicle -> ProtoHelper.serializeNullable(
                    value.vehicleAngularVelocity,
                    stream,
                    ProtoLE.Float
                )

                else -> Unit
            }
            Proto.Boolean.serialize(value.onGround, stream)
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): CorrectPlayerMovePredictionPacket {
            val predictionType: PredictionType
            return CorrectPlayerMovePredictionPacket(
                predictionType = PredictionType.deserialize(stream).also { predictionType = it },
                position = Vector3f.deserialize(stream),
                delta = Vector3f.deserialize(stream),
                rotation = when (predictionType) {
                    PredictionType.Vehicle -> Vector2f.deserialize(stream)
                    else -> null
                },
                vehicleAngularVelocity = when (predictionType) {
                    PredictionType.Vehicle -> ProtoHelper.deserializeNullable(stream, ProtoLE.Float)
                    else -> null
                },
                onGround = Proto.Boolean.deserialize(stream),
                tick = ProtoVAR.ULong.deserialize(stream),
            )
        }
    }
}