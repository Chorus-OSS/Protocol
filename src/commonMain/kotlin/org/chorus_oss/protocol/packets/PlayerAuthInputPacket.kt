package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.Vector2
import org.chorus_oss.protocol.shared.types.Vector2f
import org.chorus_oss.protocol.shared.types.Vector3f

import org.chorus_oss.protocol.types.*
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequest
import java.util.*


class PlayerAuthInputPacket : Packet(id) {
    var yaw: Float = 0f
    var pitch: Float = 0f
    var headYaw: Float = 0f
    var position: Vector3f? = null
    var motion: Vector2? = null
    var inputData: MutableSet<AuthInputAction> = EnumSet.noneOf(AuthInputAction::class.java)
    var inputMode: InputMode? = null
    var playMode: ClientPlayMode? = null
    var interactionModel: AuthInteractionModel? = null
    var interactRotation: Vector2f? = null
    var tick: PlayerInputTick? = null
    var delta: Vector3f? = null

    /**
     * [.inputData] must contain [AuthInputAction.PERFORM_ITEM_STACK_REQUEST] in order for this to not be null.
     */
    var itemStackRequest: ItemStackRequest? = null
    val blockActionData: MutableMap<PlayerActionType, PlayerBlockActionData> = EnumMap(
        PlayerActionType::class.java
    )
    var analogMoveVector: Vector2f? = null
    var predictedVehicle: Long = 0
    var vehicleRotation: Vector2f? = null
    var cameraOrientation: Vector3f? = null
    var rawMoveVector: Vector2f? = null

    override fun pid(): Int {
        return ProtocolInfo.PLAYER_AUTH_INPUT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PlayerAuthInputPacket> {
        override fun decode(byteBuf: ByteBuf): PlayerAuthInputPacket {
            val packet = PlayerAuthInputPacket()

            packet.pitch = byteBuf.readFloatLE()
            packet.yaw = byteBuf.readFloatLE()
            packet.position = byteBuf.readVector3f()
            packet.motion = Vector2(byteBuf.readFloatLE().toDouble(), byteBuf.readFloatLE().toDouble())
            packet.headYaw = byteBuf.readFloatLE()

            val inputData = byteBuf.readUnsignedBigVarInt(AuthInputAction.Companion.size())
            for (i in 0..<AuthInputAction.Companion.size()) {
                if (inputData.testBit(i)) {
                    packet.inputData.add(AuthInputAction.Companion.from(i))
                }
            }

            packet.inputMode = InputMode.Companion.fromOrdinal(byteBuf.readUnsignedVarInt())
            packet.playMode = ClientPlayMode.Companion.fromOrdinal(byteBuf.readUnsignedVarInt())
            packet.interactionModel = AuthInteractionModel.Companion.fromOrdinal(byteBuf.readUnsignedVarInt())

            packet.interactRotation = byteBuf.readVector2f()

            packet.tick = byteBuf.readPlayerInputTick()
            packet.delta = byteBuf.readVector3f()

            if (packet.inputData.contains(AuthInputAction.PERFORM_ITEM_STACK_REQUEST)) {
                packet.itemStackRequest = byteBuf.readItemStackRequest()
            }

            if (packet.inputData.contains(AuthInputAction.PERFORM_BLOCK_ACTIONS)) {
                val arraySize = byteBuf.readVarInt()
                for (i in 0..<arraySize) {
                    when (val type: PlayerActionType = PlayerActionType.from(byteBuf.readVarInt())) {
                        PlayerActionType.START_DESTROY_BLOCK,
                        PlayerActionType.ABORT_DESTROY_BLOCK,
                        PlayerActionType.CRACK_BLOCK,
                        PlayerActionType.PREDICT_DESTROY_BLOCK,
                        PlayerActionType.CONTINUE_DESTROY_BLOCK ->
                            packet.blockActionData[type] =
                                PlayerBlockActionData(type, byteBuf.readSignedBlockPosition(), byteBuf.readVarInt())

                        else -> Unit
                    }
                }
            }

            if (packet.inputData.contains(AuthInputAction.IN_CLIENT_PREDICTED_IN_VEHICLE)) {
                packet.vehicleRotation = byteBuf.readVector2f()
                packet.predictedVehicle = byteBuf.readVarLong()
            }

            packet.analogMoveVector = byteBuf.readVector2f()
            packet.cameraOrientation = byteBuf.readVector3f()
            packet.rawMoveVector = byteBuf.readVector2f()

            return packet
        }
    }
}