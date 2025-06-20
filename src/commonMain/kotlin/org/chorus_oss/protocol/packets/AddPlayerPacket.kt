package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.*
import org.chorus_oss.protocol.types.actor_data.ActorDataMap
import org.chorus_oss.protocol.types.item.ItemStack
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class AddPlayerPacket(
    val uuid: Uuid,
    val playerName: String,
    val actorRuntimeID: ULong,
    val platformChatID: String,
    val position: Vector3f,
    val velocity: Vector3f,
    val rotation: Vector2f,
    val headYaw: Float,
    val carriedItem: ItemStack,
    val playerGameType: Int,
    val actorData: ActorDataMap,
    val actorProperties: ActorProperties,
    val abilitiesData: AbilitiesData,
    val actorLinks: List<ActorLink>,
    val deviceID: String,
    val buildPlatform: Platform,
) : Packet(id) {
    companion object : PacketCodec<AddPlayerPacket> {
        override val id: Int = 12

        override fun deserialize(stream: Source): AddPlayerPacket {
            return AddPlayerPacket(
                uuid = Proto.Uuid.deserialize(stream),
                playerName = Proto.String.deserialize(stream),
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                platformChatID = Proto.String.deserialize(stream),
                position = Vector3f.deserialize(stream),
                velocity = Vector3f.deserialize(stream),
                rotation = Vector2f.deserialize(stream),
                headYaw = ProtoLE.Float.deserialize(stream),
                carriedItem = ItemStack.deserialize(stream),
                playerGameType = ProtoVAR.Int.deserialize(stream),
                actorData = ActorDataMap.deserialize(stream),
                actorProperties = ActorProperties.deserialize(stream),
                abilitiesData = AbilitiesData.deserialize(stream),
                actorLinks = ProtoHelper.deserializeList(stream, ActorLink),
                deviceID = Proto.String.deserialize(stream),
                buildPlatform = Platform.deserialize(stream)
            )
        }

        @OptIn(ExperimentalUuidApi::class)
        override fun serialize(value: AddPlayerPacket, stream: Sink) {
            Proto.Uuid.serialize(value.uuid, stream)
            Proto.String.serialize(value.playerName, stream)
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            Proto.String.serialize(value.platformChatID, stream)
            Vector3f.serialize(value.position, stream)
            Vector3f.serialize(value.velocity, stream)
            Vector2f.serialize(value.rotation, stream)
            ProtoLE.Float.serialize(value.headYaw, stream)
            ItemStack.serialize(value.carriedItem, stream)
            ProtoVAR.Int.serialize(value.playerGameType, stream)
            ActorDataMap.serialize(value.actorData, stream)
            ActorProperties.serialize(value.actorProperties, stream)
            AbilitiesData.serialize(value.abilitiesData, stream)
            ProtoHelper.serializeList(value.actorLinks, stream, ActorLink)
            Proto.String.serialize(value.deviceID, stream)
            Platform.serialize(value.buildPlatform, stream)
        }
    }
}
