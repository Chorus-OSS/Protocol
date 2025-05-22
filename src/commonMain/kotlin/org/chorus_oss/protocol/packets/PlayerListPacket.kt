package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.Color
import org.chorus_oss.protocol.types.IColorRGBA
import org.chorus_oss.protocol.types.Platform
import org.chorus_oss.protocol.types.skin.Skin
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
data class PlayerListPacket(
    val actionType: ActionType,
    val addPlayerList: List<AddPlayerEntry>?,
    val trustedSkinList: List<Boolean>?,
    val removePlayerList: List<Uuid>?,
) : Packet(id) {
    companion object : PacketCodec<PlayerListPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class ActionType {
            Add,
            Remove;

            companion object : ProtoCodec<ActionType> {
                override fun serialize(
                    value: ActionType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): ActionType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        @OptIn(ExperimentalUuidApi::class)
        data class AddPlayerEntry(
            val uuid: Uuid,
            val actorUniqueID: ActorUniqueID,
            val playerName: String,
            val xuid: String,
            val platformChatID: String,
            val buildPlatform: Platform,
            val skin: Skin,
            val isTeacher: Boolean,
            val isHost: Boolean,
            val isSubClient: Boolean,
            val playerColor: Color,
        ) {
            companion object : ProtoCodec<AddPlayerEntry> {
                override fun serialize(
                    value: AddPlayerEntry,
                    stream: Sink
                ) {
                    Proto.Uuid.serialize(value.uuid, stream)
                    ActorUniqueID.serialize(value.actorUniqueID, stream)
                    Proto.String.serialize(value.playerName, stream)
                    Proto.String.serialize(value.xuid, stream)
                    Proto.String.serialize(value.platformChatID, stream)
                    Platform.serialize(value.buildPlatform, stream)
                    Skin.serialize(value.skin, stream)
                    Proto.Boolean.serialize(value.isTeacher, stream)
                    Proto.Boolean.serialize(value.isHost, stream)
                    Proto.Boolean.serialize(value.isSubClient, stream)
                    IColorRGBA.serialize(value.playerColor, stream)
                }

                override fun deserialize(stream: Source): AddPlayerEntry {
                    return AddPlayerEntry(
                        uuid = Proto.Uuid.deserialize(stream),
                        actorUniqueID = ActorUniqueID.deserialize(stream),
                        playerName = Proto.String.deserialize(stream),
                        xuid = Proto.String.deserialize(stream),
                        platformChatID = Proto.String.deserialize(stream),
                        buildPlatform = Platform.deserialize(stream),
                        skin = Skin.deserialize(stream),
                        isTeacher = Proto.Boolean.deserialize(stream),
                        isHost = Proto.Boolean.deserialize(stream),
                        isSubClient = Proto.Boolean.deserialize(stream),
                        playerColor = IColorRGBA.deserialize(stream),
                    )
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.PLAYER_LIST_PACKET

        override fun serialize(value: PlayerListPacket, stream: Sink) {
            ActionType.serialize(value.actionType, stream)
            when (value.actionType) {
                ActionType.Add -> {
                    ProtoHelper.serializeList(
                        value.addPlayerList as List<AddPlayerEntry>, stream,
                        AddPlayerEntry
                    )
                    ProtoHelper.serializeList(
                        value.trustedSkinList as List<Boolean>,
                        stream,
                        Proto.Boolean
                    )
                }

                ActionType.Remove -> ProtoHelper.serializeList(value.removePlayerList as List<Uuid>, stream, Proto.Uuid)
            }
        }

        override fun deserialize(stream: Source): PlayerListPacket {
            val actionType: ActionType
            return PlayerListPacket(
                actionType = ActionType.deserialize(stream).also { actionType = it },
                addPlayerList = when (actionType) {
                    ActionType.Add -> ProtoHelper.deserializeList(stream, AddPlayerEntry)
                    else -> null
                },
                trustedSkinList = when (actionType) {
                    ActionType.Add -> ProtoHelper.deserializeList(stream, Proto.Boolean)
                    else -> null
                },
                removePlayerList = when (actionType) {
                    ActionType.Remove -> ProtoHelper.deserializeList(stream, Proto.Uuid)
                    else -> null
                }
            )
        }
    }
}
