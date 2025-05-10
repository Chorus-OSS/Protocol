package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.shared.types.IVector3
import org.chorus_oss.protocol.shared.types.UIVector3

import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.Color
import org.chorus_oss.protocol.types.IVarColorRGBA

data class ClientboundMapItemDataPacket(
    val mapID: ActorUniqueID,
    val typeFlags: UInt,
    val dimension: Byte,
    val isLockedMap: Boolean,
    val mapOrigin: IVector3,
    val scale: Byte? = null,
    val creationData: CreationData? = null,
    val decorationUpdateData: DecorationUpdateData? = null,
    val textureUpdateData: TextureUpdateData? = null,
) {
    companion object : PacketCodec<ClientboundMapItemDataPacket> {
        enum class Type(val bit: UInt) {
            INVALID(0u),
            TEXTURE_UPDATE(1u shl 1),
            DECORATION_UPDATE(1u shl 2),
            CREATION(1u shl 3);
        }

        data class CreationData(
            val mapIDList: List<ActorUniqueID>,
        )

        data class DecorationUpdateData(
            val actorIDs: List<MapItemTrackedActor>,
            val decorationList: List<MapDecoration>,
        )

        data class TextureUpdateData(
            val textureWidth: Int,
            val textureHeight: Int,
            val xTexCoordinate: Int,
            val yTexCoordinate: Int,
            val pixels: List<Color>
        )

        data class MapDecoration(
            val type: Type,
            val rotation: Byte,
            val x: Byte,
            val y: Byte,
            val label: String,
            val color: Color,
        ) {
            companion object : ProtoCodec<MapDecoration> {
                enum class Type(val id: Byte) {
                    MARKER_WHITE(0),
                    MARKER_GREEN(1),
                    MARKER_RED(2),
                    MARKER_BLUE(3),
                    X_WHITE(4),
                    TRIANGLE_RED(5),
                    SQUARE_WHITE(6),
                    MARKER_SIGN(7),
                    MARKER_PINK(8),
                    MARKER_ORANGE(9),
                    MARKER_YELLOW(10),
                    MARKER_TEAL(11),
                    TRIANGLE_GREEN(12),
                    SMALL_SQUARE_WHITE(13),
                    MANSION(14),
                    MONUMENT(15),
                    NO_DRAW(16),
                    VILLAGE_DESERT(17),
                    VILLAGE_PLAINS(18),
                    VILLAGE_SAVANNA(19),
                    VILLAGE_SNOWY(20),
                    VILLAGE_TAIGA(21),
                    JUNGLE_TEMPLE(22),
                    WITCH_HUT(23),
                    TRIAL_CHAMBERS(24);

                    companion object : ProtoCodec<Type> {
                        val PLAYER = MARKER_WHITE
                        val PLAYER_OFF_MAP = SQUARE_WHITE
                        val PLAYER_OFF_LIMITS = SMALL_SQUARE_WHITE
                        val PLAYER_HIDDEN = NO_DRAW
                        val ITEM_FRAME = MARKER_GREEN

                        override fun serialize(value: Type, stream: Buffer) {
                            Proto.Byte.serialize(value.ordinal.toByte(), stream)
                        }

                        override fun deserialize(stream: Buffer): Type {
                            return entries[Proto.Byte.deserialize(stream).toInt()]
                        }
                    }
                }

                override fun serialize(value: MapDecoration, stream: Buffer) {
                    Type.serialize(value.type, stream)
                    Proto.Byte.serialize(value.rotation, stream)
                    Proto.Byte.serialize(value.x, stream)
                    Proto.Byte.serialize(value.y, stream)
                    Proto.String.serialize(value.label, stream)
                    IVarColorRGBA.serialize(value.color, stream)
                }

                override fun deserialize(stream: Buffer): MapDecoration {
                    return MapDecoration(
                        type = Type.deserialize(stream),
                        rotation = Proto.Byte.deserialize(stream),
                        x = Proto.Byte.deserialize(stream),
                        y = Proto.Byte.deserialize(stream),
                        label = Proto.String.deserialize(stream),
                        color = IVarColorRGBA.deserialize(stream),
                    )
                }
            }
        }


        data class MapItemTrackedActor(
            val type: Type,
            val data: Data,
        ) {
            companion object : ProtoCodec<MapItemTrackedActor> {
                enum class Type {
                    ENTITY,
                    BLOCK;

                    companion object : ProtoCodec<Type> {
                        override fun serialize(value: Type, stream: Buffer) {
                            ProtoLE.Int.serialize(value.ordinal, stream)
                        }

                        override fun deserialize(stream: Buffer): Type {
                            return entries[ProtoLE.Int.deserialize(stream)]
                        }
                    }
                }

                interface Data
                data class EntityData(
                    val uniqueID: ActorUniqueID
                ) : Data

                data class BlockData(
                    val blockPosition: IVector3,
                ) : Data

                override fun serialize(value: MapItemTrackedActor, stream: Buffer) {
                    Type.serialize(value.type, stream)
                    when (value.type) {
                        Type.ENTITY -> {
                            val entityData = value.data as EntityData
                            ActorUniqueID.serialize(entityData.uniqueID, stream)
                        }
                        Type.BLOCK -> {
                            val blockData = value.data as BlockData
                            UIVector3.serialize(blockData.blockPosition, stream)
                        }
                    }
                }

                override fun deserialize(stream: Buffer): MapItemTrackedActor {
                    val type: Type
                    return MapItemTrackedActor(
                        type = Type.deserialize(stream).also { type = it },
                        data = when (type) {
                            Type.ENTITY -> EntityData(
                                uniqueID = ActorUniqueID.deserialize(stream)
                            )
                            Type.BLOCK -> BlockData(
                                blockPosition = UIVector3.deserialize(stream)
                            )
                        }
                    )
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.CLIENTBOUND_MAP_ITEM_DATA_PACKET

        override fun deserialize(stream: Buffer): ClientboundMapItemDataPacket {
            val typeFlags: UInt
            return ClientboundMapItemDataPacket(
                mapID = ActorUniqueID.deserialize(stream),
                typeFlags = ProtoVAR.UInt.deserialize(stream).also { typeFlags = it },
                dimension = Proto.Byte.deserialize(stream),
                isLockedMap = Proto.Boolean.deserialize(stream),
                mapOrigin = IVector3.deserialize(stream),

                creationData = when {
                    typeFlags and Type.CREATION.bit != 0u -> CreationData(
                        mapIDList = ProtoHelper.deserializeList(stream, ActorUniqueID::deserialize)
                    )
                    else -> null
                },

                scale = when {
                    typeFlags and (Type.CREATION.bit or Type.DECORATION_UPDATE.bit or Type.TEXTURE_UPDATE.bit) != 0u -> Proto.Byte.deserialize(stream)
                    else -> null
                },

                decorationUpdateData = when {
                    typeFlags and Type.DECORATION_UPDATE.bit != 0u -> DecorationUpdateData(
                        actorIDs = ProtoHelper.deserializeList(stream, MapItemTrackedActor::deserialize),
                        decorationList = ProtoHelper.deserializeList(stream, MapDecoration::deserialize)
                    )
                    else -> null
                },

                textureUpdateData = when {
                    typeFlags and Type.TEXTURE_UPDATE.bit != 0u -> TextureUpdateData(
                        textureWidth = ProtoVAR.Int.deserialize(stream),
                        textureHeight = ProtoVAR.Int.deserialize(stream),
                        xTexCoordinate = ProtoVAR.Int.deserialize(stream),
                        yTexCoordinate = ProtoVAR.Int.deserialize(stream),
                        pixels = ProtoHelper.deserializeList(stream, IVarColorRGBA::deserialize)
                    )
                    else -> null
                }
            )
        }

        override fun serialize(value: ClientboundMapItemDataPacket, stream: Buffer) {
            ActorUniqueID.serialize(value.mapID, stream)
            ProtoVAR.UInt.serialize(value.typeFlags, stream)
            Proto.Byte.serialize(value.dimension, stream)
            Proto.Boolean.serialize(value.isLockedMap, stream)
            IVector3.serialize(value.mapOrigin, stream)

            if (value.typeFlags and Type.CREATION.bit != 0u) {
                val creationData = value.creationData as CreationData
                ProtoHelper.serializeList(creationData.mapIDList, stream, ActorUniqueID::serialize)
            }

            if (value.typeFlags and (Type.CREATION.bit or Type.DECORATION_UPDATE.bit or Type.TEXTURE_UPDATE.bit) != 0u) {
                val scale = value.scale as Byte
                Proto.Byte.serialize(scale, stream)
            }

            if (value.typeFlags and Type.DECORATION_UPDATE.bit != 0u) {
                val decorationUpdateData = value.decorationUpdateData as DecorationUpdateData
                ProtoHelper.serializeList(decorationUpdateData.actorIDs, stream, MapItemTrackedActor::serialize)
                ProtoHelper.serializeList(decorationUpdateData.decorationList, stream, MapDecoration::serialize)
            }

            if (value.typeFlags and Type.TEXTURE_UPDATE.bit != 0u) {
                val textureUpdateData = value.textureUpdateData as TextureUpdateData
                ProtoVAR.Int.serialize(textureUpdateData.textureWidth, stream)
                ProtoVAR.Int.serialize(textureUpdateData.textureHeight, stream)
                ProtoVAR.Int.serialize(textureUpdateData.xTexCoordinate, stream)
                ProtoVAR.Int.serialize(textureUpdateData.yTexCoordinate, stream)
                ProtoHelper.serializeList(textureUpdateData.pixels, stream, IVarColorRGBA::serialize)
            }
        }
    }
}
