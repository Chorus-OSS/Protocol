package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.PlayerActionType
import org.chorus_oss.protocol.types.UIVector3


data class PlayerActionPacket(
    val entityRuntimeID: ActorRuntimeID,
    val actionType: PlayerActionType,
    val blockPosition: IVector3,
    val resultPosition: IVector3,
    val blockFace: Int,
) : Packet(id) {
    companion object : PacketCodec<PlayerActionPacket> {
        override val id: Int = 36

        override fun serialize(value: PlayerActionPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            PlayerActionType.serialize(value.actionType, stream)
            UIVector3.serialize(value.blockPosition, stream)
            UIVector3.serialize(value.resultPosition, stream)
            ProtoVAR.Int.serialize(value.blockFace, stream)
        }

        override fun deserialize(stream: Source): PlayerActionPacket {
            return PlayerActionPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                actionType = PlayerActionType.deserialize(stream),
                blockPosition = UIVector3.deserialize(stream),
                resultPosition = UIVector3.deserialize(stream),
                blockFace = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
