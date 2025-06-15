package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.BitSet
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.*
import org.chorus_oss.protocol.types.inventory.transaction.UseItemTransactionData
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequest


data class PlayerAuthInputPacket(
    val pitch: Float,
    val yaw: Float,
    val position: Vector3f,
    val moveVector: Vector2f,
    val headYaw: Float,
    val inputData: BitSet,
    val inputMode: InputMode,
    val playMode: PlayMode,
    val interactionModel: InteractionModel,
    val interactPitch: Float,
    val interactYaw: Float,
    val tick: ULong,
    val delta: Vector3f,
    val itemInteractionData: UseItemTransactionData?,
    val itemStackRequest: ItemStackRequest?,
    val blockActions: List<PlayerBlockActionData>?,
    val vehicleRotation: Vector2f?,
    val clientPredictedVehicle: ActorUniqueID?,
    val analogMoveVector: Vector2f,
    val cameraOrientation: Vector3f,
    val rawMoveVector: Vector2f,
) : Packet(id) {
    companion object : PacketCodec<PlayerAuthInputPacket> {
        override val id: Int = 144

        override fun serialize(value: PlayerAuthInputPacket, stream: Sink) {
            ProtoLE.Float.serialize(value.pitch, stream)
            ProtoLE.Float.serialize(value.yaw, stream)
            Vector3f.serialize(value.position, stream)
            Vector2f.serialize(value.moveVector, stream)
            ProtoLE.Float.serialize(value.headYaw, stream)
            BitSet.serialize(value.inputData, stream)
            InputMode.serialize(value.inputMode, stream)
            PlayMode.serialize(value.playMode, stream)
            InteractionModel.serialize(value.interactionModel, stream)
            ProtoLE.Float.serialize(value.interactPitch, stream)
            ProtoLE.Float.serialize(value.interactYaw, stream)
            ProtoVAR.ULong.serialize(value.tick, stream)
            Vector3f.serialize(value.delta, stream)

            when (value.inputData.getOrElse(InputFlag.PerformItemInteraction.ordinal) { false }) {
                true -> UseItemTransactionData.serialize(value.itemInteractionData as UseItemTransactionData, stream)
                false -> Unit
            }

            when (value.inputData.getOrElse(InputFlag.PerformItemStackRequest.ordinal) { false }) {
                true -> ItemStackRequest.serialize(value.itemStackRequest as ItemStackRequest, stream)
                false -> Unit
            }

            when (value.inputData.getOrElse(InputFlag.PerformBlockActions.ordinal) { false }) {
                true -> {
                    val blockActions = value.blockActions as List<PlayerBlockActionData>
                    ProtoVAR.Int.serialize(blockActions.size, stream)
                    blockActions.forEach { PlayerBlockActionData.serialize(it, stream) }
                }

                false -> Unit
            }

            when (value.inputData.getOrElse(InputFlag.ClientPredictedVehicle.ordinal) { false }) {
                true -> Vector2f.serialize(value.vehicleRotation as Vector2f, stream)
                false -> Unit
            }

            when (value.inputData.getOrElse(InputFlag.ClientPredictedVehicle.ordinal) { false }) {
                true -> ActorUniqueID.serialize(value.clientPredictedVehicle as ActorUniqueID, stream)
                false -> Unit
            }

            Vector2f.serialize(value.analogMoveVector, stream)
            Vector3f.serialize(value.cameraOrientation, stream)
            Vector2f.serialize(value.rawMoveVector, stream)
        }

        override fun deserialize(stream: Source): PlayerAuthInputPacket {
            val inputData: BitSet
            return PlayerAuthInputPacket(
                pitch = ProtoLE.Float.deserialize(stream),
                yaw = ProtoLE.Float.deserialize(stream),
                position = Vector3f.deserialize(stream),
                moveVector = Vector2f.deserialize(stream),
                headYaw = ProtoLE.Float.deserialize(stream),
                inputData = BitSet.deserialize(stream).also { inputData = it },
                inputMode = InputMode.deserialize(stream),
                playMode = PlayMode.deserialize(stream),
                interactionModel = InteractionModel.deserialize(stream),
                interactPitch = ProtoLE.Float.deserialize(stream),
                interactYaw = ProtoLE.Float.deserialize(stream),
                tick = ProtoVAR.ULong.deserialize(stream),
                delta = Vector3f.deserialize(stream),
                itemInteractionData = when (inputData.getOrElse(InputFlag.PerformItemInteraction.ordinal) { false }) {
                    true -> UseItemTransactionData.deserialize(stream)
                    false -> null
                },
                itemStackRequest = when (inputData.getOrElse(InputFlag.PerformItemStackRequest.ordinal) { false }) {
                    true -> ItemStackRequest.deserialize(stream)
                    false -> null
                },
                blockActions = when (inputData.getOrElse(InputFlag.PerformBlockActions.ordinal) { false }) {
                    true -> List(ProtoVAR.Int.deserialize(stream)) {
                        PlayerBlockActionData.deserialize(stream)
                    }

                    false -> null
                },
                vehicleRotation = when (inputData.getOrElse(InputFlag.ClientPredictedVehicle.ordinal) { false }) {
                    true -> Vector2f.deserialize(stream)
                    false -> null
                },
                clientPredictedVehicle = when (inputData.getOrElse(InputFlag.ClientPredictedVehicle.ordinal) { false }) {
                    true -> ActorUniqueID.deserialize(stream)
                    false -> null
                },
                analogMoveVector = Vector2f.deserialize(stream),
                cameraOrientation = Vector3f.deserialize(stream),
                rawMoveVector = Vector2f.deserialize(stream),
            )
        }
    }
}