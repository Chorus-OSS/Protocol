package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorRuntimeID


data class NPCRequestPacket(
    val entityRuntimeID: ActorRuntimeID,
    val requestType: RequestType,
    val commandString: String,
    val actionType: Byte,
    val sceneName: String,
) : Packet(id) {
    companion object : PacketCodec<NPCRequestPacket> {
        enum class RequestType {
            SetActions,
            ExecuteAction,
            ExecuteClosingCommands,
            SetName,
            SetSkin,
            SetInteractText,
            ExecuteOpeningCommands;

            companion object : ProtoCodec<RequestType> {
                override fun serialize(
                    value: RequestType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): RequestType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 98

        override fun serialize(value: NPCRequestPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            RequestType.serialize(value.requestType, stream)
            Proto.String.serialize(value.commandString, stream)
            Proto.Byte.serialize(value.actionType, stream)
            Proto.String.serialize(value.sceneName, stream)
        }

        override fun deserialize(stream: Source): NPCRequestPacket {
            return NPCRequestPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                requestType = RequestType.deserialize(stream),
                commandString = Proto.String.deserialize(stream),
                actionType = Proto.Byte.deserialize(stream),
                sceneName = Proto.String.deserialize(stream),
            )
        }
    }
}
