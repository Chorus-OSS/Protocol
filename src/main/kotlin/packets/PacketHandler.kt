package org.chorus_oss.protocol.packets

interface PacketHandler {
    fun handle(pk: AddBehaviorTreePacket) {}

    fun handle(pk: AddActorPacket) {}

    fun handle(pk: AddItemActorPacket) {}

    fun handle(pk: AddPaintingPacket) {}

    fun handle(pk: AddPlayerPacket) {}

    fun handle(pk: AddVolumeEntityPacket) {}

    fun handle(pk: AgentActionEventPacket) {}

    fun handle(pk: AgentAnimationPacket) {}

    fun handle(pk: AnimateEntityPacket) {}

    fun handle(pk: AnimatePacket) {}

    fun handle(pk: AnvilDamagePacket) {}

    fun handle(pk: AvailableCommandsPacket) {}

    fun handle(pk: AvailableActorIdentifiersPacket) {}

    fun handle(pk: BiomeDefinitionListPacket) {}

    fun handle(pk: BlockActorDataPacket) {}

    fun handle(pk: BlockEventPacket) {}

    fun handle(pk: BlockPickRequestPacket) {}

    fun handle(pk: BookEditPacket) {}

    fun handle(pk: BossEventPacket) {}

    fun handle(pk: CameraAimAssistPacket) {}

    fun handle(pk: CameraAimAssistPresetsPacket) {}

    fun handle(pk: CameraInstructionPacket) {}

    fun handle(pk: CameraPacket) {}

    fun handle(pk: CameraPresetsPacket) {}

    fun handle(pk: CameraShakePacket) {}

    fun handle(pk: ChangeDimensionPacket) {}

    fun handle(pk: ChangeMobPropertyPacket) {}

    fun handle(pk: ChunkRadiusUpdatedPacket) {}

    fun handle(pk: ClientboundCloseFormPacket) {}

    fun handle(pk: ClientCacheStatusPacket) {}

    fun handle(pk: ClientToServerHandshakePacket) {}

    fun handle(pk: ClientboundMapItemDataPacket) {}

    fun handle(pk: CodeBuilderPacket) {}

    fun handle(pk: CodeBuilderSourcePacket) {}

    fun handle(pk: CommandBlockUpdatePacket) {}

    fun handle(pk: CommandOutputPacket) {}

    fun handle(pk: CommandRequestPacket) {}

    fun handle(pk: CompletedUsingItemPacket) {}

    fun handle(pk: CompressedBiomeDefinitionListPacket) {}

    fun handle(pk: ContainerClosePacket) {}

    fun handle(pk: ContainerOpenPacket) {}

    fun handle(pk: ContainerRegistryCleanupPacket) {}

    fun handle(pk: ContainerSetDataPacket) {}

    fun handle(pk: CraftingDataPacket) {}

    fun handle(pk: CreatePhotoPacket) {}

    fun handle(pk: CreativeContentPacket) {}

    fun handle(pk: DeathInfoPacket) {}

    fun handle(pk: DebugInfoPacket) {}

    fun handle(pk: ServerboundDiagnosticsPacket) {}

    fun handle(pk: DimensionDataPacket) {}

    fun handle(pk: DisconnectPacket) {}

    fun handle(pk: EduUriResourcePacket) {}

    fun handle(pk: EmoteListPacket) {}

    fun handle(pk: EmotePacket) {}

    fun handle(pk: EntityEventPacket) {}

    fun handle(pk: ActorPickRequestPacket) {}

    fun handle(pk: GUIDataPickItemPacket) {}

    fun handle(pk: GameRulesChangedPacket) {}

    fun handle(pk: HurtArmorPacket) {}

    fun handle(pk: InitiateWebSocketConnectionPacket) {}

    fun handle(pk: InteractPacket) {}

    fun handle(pk: InventoryContentPacket) {}

    fun handle(pk: InventorySlotPacket) {}

    fun handle(pk: InventoryTransactionPacket) {}

    fun handle(pk: ItemRegistryPacket) {}

    fun handle(pk: ItemStackRequestPacket) {}

    fun handle(pk: ItemStackResponsePacket) {}

    fun handle(pk: LabTablePacket) {}

    fun handle(pk: LecternUpdatePacket) {}

    fun handle(pk: LessonProgressPacket) {}

    fun handle(pk: LevelChunkPacket) {}

    fun handle(pk: LevelEventGenericPacket) {}

    fun handle(pk: LevelEventPacket) {}

    fun handle(pk: LevelSoundEventPacket) {}

    fun handle(pk: LoginPacket) {}

    fun handle(pk: MapCreateLockedCopyPacket) {}

    fun handle(pk: MapInfoRequestPacket) {}

    fun handle(pk: MobArmorEquipmentPacket) {}

    fun handle(pk: MobEffectPacket) {}

    fun handle(pk: MobEquipmentPacket) {}

    fun handle(pk: ModalFormRequestPacket) {}

    fun handle(pk: ModalFormResponsePacket) {}

    fun handle(pk: MoveEntityAbsolutePacket) {}

    fun handle(pk: MoveEntityDeltaPacket) {}

    fun handle(pk: MovementEffectPacket) {}

    fun handle(pk: MovePlayerPacket) {}

    fun handle(pk: NPCDialoguePacket) {}

    fun handle(pk: NPCRequestPacket) {}

    fun handle(pk: NetworkChunkPublisherUpdatePacket) {}

    fun handle(pk: NetworkSettingsPacket) {}

    fun handle(pk: OnScreenTextureAnimationPacket) {}

    fun handle(pk: OpenSignPacket) {}

    fun handle(pk: PacketViolationWarningPacket) {}

    fun handle(pk: PlaySoundPacket) {}

    fun handle(pk: PlayStatusPacket) {}

    fun handle(pk: PlayerActionPacket) {}

    fun handle(pk: PlayerArmorDamagePacket) {}

    fun handle(pk: PlayerAuthInputPacket) {}

    fun handle(pk: PlayerEnchantOptionsPacket) {}

    fun handle(pk: PlayerFogPacket) {}

    fun handle(pk: PlayerHotbarPacket) {}

    fun handle(pk: PlayerInputPacket) {}

    fun handle(pk: PlayerListPacket) {}

    fun handle(pk: PlayerSkinPacket) {}

    fun handle(pk: PlayerStartItemCoolDownPacket) {}

    fun handle(pk: PositionTrackingDBClientRequestPacket) {}

    fun handle(pk: PositionTrackingDBServerBroadcastPacket) {}

    fun handle(pk: RemoveEntityPacket) {}

    fun handle(pk: RemoveObjectivePacket) {}

    fun handle(pk: RemoveVolumeEntityPacket) {}

    fun handle(pk: RequestAbilityPacket) {}

    fun handle(pk: RequestChunkRadiusPacket) {}

    fun handle(pk: RequestNetworkSettingsPacket) {}

    fun handle(pk: RequestPermissionsPacket) {}

    fun handle(pk: ResourcePackChunkDataPacket) {}

    fun handle(pk: ResourcePackChunkRequestPacket) {}

    fun handle(pk: ResourcePackClientResponsePacket) {}

    fun handle(pk: ResourcePackDataInfoPacket) {}

    fun handle(pk: ResourcePackStackPacket) {}

    fun handle(pk: ResourcePacksInfoPacket) {}

    fun handle(pk: RespawnPacket) {}

    fun handle(pk: RiderJumpPacket) {}

    fun handle(pk: ScriptMessagePacket) {}

    fun handle(pk: ServerPostMovePositionPacket) {}

    fun handle(pk: ServerSettingsRequestPacket) {}

    fun handle(pk: ServerSettingsResponsePacket) {}

    fun handle(pk: ServerToClientHandshakePacket) {}

    fun handle(pk: SetCommandsEnabledPacket) {}

    fun handle(pk: SetDefaultGameTypePacket) {}

    fun handle(pk: SetDifficultyPacket) {}

    fun handle(pk: SetDisplayObjectivePacket) {}

    fun handle(pk: SetEntityDataPacket) {}

    fun handle(pk: SetEntityLinkPacket) {}

    fun handle(pk: SetEntityMotionPacket) {}

    fun handle(pk: SetHealthPacket) {}

    fun handle(pk: SetHudPacket) {}

    fun handle(pk: SetLastHurtByPacket) {}

    fun handle(pk: SetLocalPlayerAsInitializedPacket) {}

    fun handle(pk: SetPlayerGameTypePacket) {}

    fun handle(pk: SetPlayerInventoryOptionsPacket) {}

    fun handle(pk: SetScorePacket) {}

    fun handle(pk: SetScoreboardIdentityPacket) {}

    fun handle(pk: SetSpawnPositionPacket) {}

    fun handle(pk: SetTimePacket) {}

    fun handle(pk: SetTitlePacket) {}

    fun handle(pk: ShowCreditsPacket) {}

    fun handle(pk: ShowProfilePacket) {}

    fun handle(pk: SimpleEventPacket) {}

    fun handle(pk: SimulationTypePacket) {}

    fun handle(pk: SpawnParticleEffectPacket) {}

    fun handle(pk: StartGamePacket) {}

    fun handle(pk: StopSoundPacket) {}

    fun handle(pk: StructureBlockUpdatePacket) {}

    fun handle(pk: SubClientLoginPacket) {}

    fun handle(pk: SyncEntityPropertyPacket) {}

    fun handle(pk: TakeItemEntityPacket) {}

    fun handle(pk: TextPacket) {}

    fun handle(pk: TickingAreasLoadStatusPacket) {}

    fun handle(pk: ToastRequestPacket) {}

    fun handle(pk: ToggleCrafterSlotRequestPacket) {}

    fun handle(pk: TransferPacket) {}

    fun handle(pk: TrimDataPacket) {}

    fun handle(pk: UnlockedRecipesPacket) {}

    fun handle(pk: UpdateAbilitiesPacket) {}

    fun handle(pk: UpdateAdventureSettingsPacket) {}

    fun handle(pk: UpdateAttributesPacket) {}

    fun handle(pk: UpdateBlockPacket) {}

    fun handle(pk: UpdateBlockSyncedPacket) {}

    fun handle(pk: UpdateClientInputLocksPacket) {}

    fun handle(pk: UpdateEquipmentPacket) {}

    fun handle(pk: UpdatePlayerGameTypePacket) {}

    fun handle(pk: UpdateSoftEnumPacket) {}

    fun handle(pk: UpdateSubChunkBlocksPacket) {}

    fun handle(pk: UpdateTradePacket) {}

    fun handle(pk: SettingsCommandPacket) {}

    fun handle(pk: ServerboundLoadingScreenPacket) {}

    fun handle(pk: ClientCameraAimAssistPacket) {}

    fun handle(pk: ClientMovementPredictionSyncPacket) {}
}
