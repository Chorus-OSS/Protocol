package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorUniqueID


data class NPCDialoguePacket(
    val entityUniqueID: ActorUniqueID,
    val actionType: ActionType,
    val dialogue: String,
    val sceneName: String,
    val npcName: String,
    val actionJSON: String,
) : Packet(id) {
    companion object : PacketCodec<NPCDialoguePacket> {
        enum class ActionType {
            Open,
            Close;

            companion object : ProtoCodec<ActionType> {
                override fun serialize(
                    value: ActionType,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): ActionType {
                    return entries[ProtoVAR.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.NPC_DIALOGUE_PACKET

        override fun serialize(value: NPCDialoguePacket, stream: Sink) {
            ActorUniqueID.serialize(value.entityUniqueID, stream)
            ActionType.serialize(value.actionType, stream)
            Proto.String.serialize(value.dialogue, stream)
            Proto.String.serialize(value.sceneName, stream)
            Proto.String.serialize(value.npcName, stream)
            Proto.String.serialize(value.actionJSON, stream)
        }

        override fun deserialize(stream: Source): NPCDialoguePacket {
            return NPCDialoguePacket(
                entityUniqueID = ActorUniqueID.deserialize(stream),
                actionType = ActionType.deserialize(stream),
                dialogue = Proto.String.deserialize(stream),
                sceneName = Proto.String.deserialize(stream),
                npcName = Proto.String.deserialize(stream),
                actionJSON = Proto.String.deserialize(stream),
            )
        }
    }
}
