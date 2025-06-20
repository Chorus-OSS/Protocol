package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.types.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class StartGamePacket(
    val entityUniqueID: Long,
    val entityRuntimeID: ULong,
    val playerGameMode: Int,
    val playerPosition: Vector3f,
    val pitch: Float,
    val yaw: Float,
    val worldSeed: Long,
    val spawnBiomeType: SpawnBiomeType,
    val userDefinedBiomeName: String,
    val dimension: Int,
    val generator: Int,
    val worldGameMode: Int,
    val hardcore: Boolean,
    val difficulty: Int,
    val worldSpawn: IVector3,
    val achievementsDisabled: Boolean,
    val editorWorldType: EditorWorldType,
    val createdInEditor: Boolean,
    val exportedFromEditor: Boolean,
    val dayCycleLockTime: Int,
    val educationEditionOffer: Int,
    val educationFeaturesEnabled: Boolean,
    val educationProductID: String,
    val rainLevel: Float,
    val lightningLevel: Float,
    val confirmedPlatformLockedContent: Boolean,
    val multiPlayerGame: Boolean,
    val lanBroadcastEnabled: Boolean,
    val xblBroadcastMode: BroadcastMode,
    val platformBroadcastMode: BroadcastMode,
    val commandsEnabled: Boolean,
    val texturePackRequired: Boolean,
    val gameRules: List<GameRule<*>>,
    val experiments: List<ExperimentData>,
    val experimentsPreviouslyToggled: Boolean,
    val bonusChestEnabled: Boolean,
    val startWithMapEnabled: Boolean,
    val playerPermissions: Int,
    val serverChunkTickRadius: Int,
    val hasLockedBehaviourPack: Boolean,
    val hasLockedTexturePack: Boolean,
    val fromLockedWorldTemplate: Boolean,
    val msaGamerTagsOnly: Boolean,
    val fromWorldTemplate: Boolean,
    val worldTemplateSettingsLocked: Boolean,
    val onlySpawnV1Villagers: Boolean,
    val personaDisabled: Boolean,
    val customSkinsDisabled: Boolean,
    val emoteChatMuted: Boolean,
    val baseGameVersion: String,
    val limitedWorldWidth: Int,
    val limitedWorldDepth: Int,
    val newNether: Boolean,
    val educationSharedResourceUriResource: EduSharedUriResource,
    val forceExperimentalGameplay: Boolean?,
    val chatRestrictionLevel: ChatRestrictionLevel,
    val disablePlayerInteractions: Boolean,
    val serverID: String,
    val worldID: String,
    val scenarioID: String,
    val levelID: String,
    val worldName: String,
    val templateContentIdentity: String,
    val trial: Boolean,
    val playerMovementSettings: PlayerMovementSettings,
    val tick: Long,
    val enchantmentSeed: Int,
    val blocks: List<BlocksEntry>,
    val multiPlayerCorrelationID: String,
    val serverAuthoritativeInventory: Boolean,
    val gameVersion: String,
    val propertyData: CompoundTag,
    val serverBlockStateChecksum: ULong,
    val clientSideGeneration: Boolean,
    val worldTemplateID: Uuid,
    val useBlockNetworkIDHashes: Boolean,
    val serverAuthoritativeSound: Boolean,
) : Packet(id) {
    companion object : PacketCodec<StartGamePacket> {
        override val id: Int = 11

        override fun serialize(value: StartGamePacket, stream: Sink) {
            ActorUniqueID.serialize(value.entityUniqueID, stream)
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            ProtoVAR.Int.serialize(value.playerGameMode, stream)
            Vector3f.serialize(value.playerPosition, stream)
            ProtoLE.Float.serialize(value.pitch, stream)
            ProtoLE.Float.serialize(value.yaw, stream)
            ProtoLE.Long.serialize(value.worldSeed, stream)
            SpawnBiomeType.serialize(value.spawnBiomeType, stream)
            Proto.String.serialize(value.userDefinedBiomeName, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            ProtoVAR.Int.serialize(value.generator, stream)
            ProtoVAR.Int.serialize(value.worldGameMode, stream)
            Proto.Boolean.serialize(value.hardcore, stream)
            ProtoVAR.Int.serialize(value.difficulty, stream)
            UIVector3.serialize(value.worldSpawn, stream)
            Proto.Boolean.serialize(value.achievementsDisabled, stream)
            EditorWorldType.serialize(value.editorWorldType, stream)
            Proto.Boolean.serialize(value.createdInEditor, stream)
            Proto.Boolean.serialize(value.exportedFromEditor, stream)
            ProtoVAR.Int.serialize(value.dayCycleLockTime, stream)
            ProtoVAR.Int.serialize(value.educationEditionOffer, stream)
            Proto.Boolean.serialize(value.educationFeaturesEnabled, stream)
            Proto.String.serialize(value.educationProductID, stream)
            ProtoLE.Float.serialize(value.rainLevel, stream)
            ProtoLE.Float.serialize(value.lightningLevel, stream)
            Proto.Boolean.serialize(value.confirmedPlatformLockedContent, stream)
            Proto.Boolean.serialize(value.multiPlayerGame, stream)
            Proto.Boolean.serialize(value.lanBroadcastEnabled, stream)
            BroadcastMode.serialize(value.xblBroadcastMode, stream)
            BroadcastMode.serialize(value.platformBroadcastMode, stream)
            Proto.Boolean.serialize(value.commandsEnabled, stream)
            Proto.Boolean.serialize(value.texturePackRequired, stream)
            ProtoHelper.serializeList(value.gameRules, stream, GameRule)
            value.experiments.let { experiments ->
                ProtoLE.UInt.serialize(experiments.size.toUInt(), stream)
                experiments.forEach { ExperimentData.serialize(it, stream) }
            }
            Proto.Boolean.serialize(value.experimentsPreviouslyToggled, stream)
            Proto.Boolean.serialize(value.bonusChestEnabled, stream)
            Proto.Boolean.serialize(value.startWithMapEnabled, stream)
            ProtoVAR.Int.serialize(value.playerPermissions, stream)
            ProtoLE.Int.serialize(value.serverChunkTickRadius, stream)
            Proto.Boolean.serialize(value.hasLockedBehaviourPack, stream)
            Proto.Boolean.serialize(value.hasLockedTexturePack, stream)
            Proto.Boolean.serialize(value.fromLockedWorldTemplate, stream)
            Proto.Boolean.serialize(value.msaGamerTagsOnly, stream)
            Proto.Boolean.serialize(value.fromWorldTemplate, stream)
            Proto.Boolean.serialize(value.worldTemplateSettingsLocked, stream)
            Proto.Boolean.serialize(value.onlySpawnV1Villagers, stream)
            Proto.Boolean.serialize(value.personaDisabled, stream)
            Proto.Boolean.serialize(value.customSkinsDisabled, stream)
            Proto.Boolean.serialize(value.emoteChatMuted, stream)
            Proto.String.serialize(value.baseGameVersion, stream)
            ProtoLE.Int.serialize(value.limitedWorldWidth, stream)
            ProtoLE.Int.serialize(value.limitedWorldDepth, stream)
            Proto.Boolean.serialize(value.newNether, stream)
            EduSharedUriResource.serialize(value.educationSharedResourceUriResource, stream)
            ProtoHelper.serializeNullable(value.forceExperimentalGameplay, stream, Proto.Boolean)
            ChatRestrictionLevel.serialize(value.chatRestrictionLevel, stream)
            Proto.Boolean.serialize(value.disablePlayerInteractions, stream)
            Proto.String.serialize(value.serverID, stream)
            Proto.String.serialize(value.worldID, stream)
            Proto.String.serialize(value.scenarioID, stream)
            Proto.String.serialize(value.levelID, stream)
            Proto.String.serialize(value.worldName, stream)
            Proto.String.serialize(value.templateContentIdentity, stream)
            Proto.Boolean.serialize(value.trial, stream)
            PlayerMovementSettings.serialize(value.playerMovementSettings, stream)
            ProtoLE.Long.serialize(value.tick, stream)
            ProtoVAR.Int.serialize(value.enchantmentSeed, stream)
            ProtoHelper.serializeList(value.blocks, stream, BlocksEntry)
            Proto.String.serialize(value.multiPlayerCorrelationID, stream)
            Proto.Boolean.serialize(value.serverAuthoritativeInventory, stream)
            Proto.String.serialize(value.gameVersion, stream)
            Tag.serialize(value.propertyData, stream, TagSerialization.NetLE, true)
            ProtoLE.ULong.serialize(value.serverBlockStateChecksum, stream)
            Proto.Uuid.serialize(value.worldTemplateID, stream)
            Proto.Boolean.serialize(value.clientSideGeneration, stream)
            Proto.Boolean.serialize(value.useBlockNetworkIDHashes, stream)
            Proto.Boolean.serialize(value.serverAuthoritativeSound, stream)
        }

        override fun deserialize(stream: Source): StartGamePacket {
            return StartGamePacket(
                entityUniqueID = ActorUniqueID.deserialize(stream),
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                playerGameMode = ProtoVAR.Int.deserialize(stream),
                playerPosition = Vector3f.deserialize(stream),
                pitch = ProtoLE.Float.deserialize(stream),
                yaw = ProtoLE.Float.deserialize(stream),
                worldSeed = ProtoLE.Long.deserialize(stream),
                spawnBiomeType = SpawnBiomeType.deserialize(stream),
                userDefinedBiomeName = Proto.String.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
                generator = ProtoVAR.Int.deserialize(stream),
                worldGameMode = ProtoVAR.Int.deserialize(stream),
                hardcore = Proto.Boolean.deserialize(stream),
                difficulty = ProtoVAR.Int.deserialize(stream),
                worldSpawn = UIVector3.deserialize(stream),
                achievementsDisabled = Proto.Boolean.deserialize(stream),
                editorWorldType = EditorWorldType.deserialize(stream),
                createdInEditor = Proto.Boolean.deserialize(stream),
                exportedFromEditor = Proto.Boolean.deserialize(stream),
                dayCycleLockTime = ProtoVAR.Int.deserialize(stream),
                educationEditionOffer = ProtoVAR.Int.deserialize(stream),
                educationFeaturesEnabled = Proto.Boolean.deserialize(stream),
                educationProductID = Proto.String.deserialize(stream),
                rainLevel = ProtoLE.Float.deserialize(stream),
                lightningLevel = ProtoLE.Float.deserialize(stream),
                confirmedPlatformLockedContent = Proto.Boolean.deserialize(stream),
                multiPlayerGame = Proto.Boolean.deserialize(stream),
                lanBroadcastEnabled = Proto.Boolean.deserialize(stream),
                xblBroadcastMode = BroadcastMode.deserialize(stream),
                platformBroadcastMode = BroadcastMode.deserialize(stream),
                commandsEnabled = Proto.Boolean.deserialize(stream),
                texturePackRequired = Proto.Boolean.deserialize(stream),
                gameRules = ProtoHelper.deserializeList(stream, GameRule),
                experiments = List(ProtoLE.UInt.deserialize(stream).toInt()) {
                    ExperimentData.deserialize(stream)
                },
                experimentsPreviouslyToggled = Proto.Boolean.deserialize(stream),
                bonusChestEnabled = Proto.Boolean.deserialize(stream),
                startWithMapEnabled = Proto.Boolean.deserialize(stream),
                playerPermissions = ProtoVAR.Int.deserialize(stream),
                serverChunkTickRadius = ProtoLE.Int.deserialize(stream),
                hasLockedBehaviourPack = Proto.Boolean.deserialize(stream),
                hasLockedTexturePack = Proto.Boolean.deserialize(stream),
                fromLockedWorldTemplate = Proto.Boolean.deserialize(stream),
                msaGamerTagsOnly = Proto.Boolean.deserialize(stream),
                fromWorldTemplate = Proto.Boolean.deserialize(stream),
                worldTemplateSettingsLocked = Proto.Boolean.deserialize(stream),
                onlySpawnV1Villagers = Proto.Boolean.deserialize(stream),
                personaDisabled = Proto.Boolean.deserialize(stream),
                customSkinsDisabled = Proto.Boolean.deserialize(stream),
                emoteChatMuted = Proto.Boolean.deserialize(stream),
                baseGameVersion = Proto.String.deserialize(stream),
                limitedWorldWidth = ProtoLE.Int.deserialize(stream),
                limitedWorldDepth = ProtoLE.Int.deserialize(stream),
                newNether = Proto.Boolean.deserialize(stream),
                educationSharedResourceUriResource = EduSharedUriResource.deserialize(stream),
                forceExperimentalGameplay = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
                chatRestrictionLevel = ChatRestrictionLevel.deserialize(stream),
                disablePlayerInteractions = Proto.Boolean.deserialize(stream),
                serverID = Proto.String.deserialize(stream),
                worldID = Proto.String.deserialize(stream),
                scenarioID = Proto.String.deserialize(stream),
                levelID = Proto.String.deserialize(stream),
                worldName = Proto.String.deserialize(stream),
                templateContentIdentity = Proto.String.deserialize(stream),
                trial = Proto.Boolean.deserialize(stream),
                playerMovementSettings = PlayerMovementSettings.deserialize(stream),
                tick = ProtoLE.Long.deserialize(stream),
                enchantmentSeed = ProtoVAR.Int.deserialize(stream),
                blocks = ProtoHelper.deserializeList(stream, BlocksEntry),
                multiPlayerCorrelationID = Proto.String.deserialize(stream),
                serverAuthoritativeInventory = Proto.Boolean.deserialize(stream),
                gameVersion = Proto.String.deserialize(stream),
                propertyData = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
                serverBlockStateChecksum = ProtoLE.ULong.deserialize(stream),
                worldTemplateID = Proto.Uuid.deserialize(stream),
                clientSideGeneration = Proto.Boolean.deserialize(stream),
                useBlockNetworkIDHashes = Proto.Boolean.deserialize(stream),
                serverAuthoritativeSound = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
