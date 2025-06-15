package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.types.*

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
) : Packet(id) {
    companion object : PacketCodec<ClientboundMapItemDataPacket> {
        enum class Type(val bit: UInt) {
            Invalid(0u),
            TextureUpdate(1u shl 1),
            DecorationUpdate(1u shl 2),
            Creation(1u shl 3);
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
                    MarkerWhite(0),
                    MarkerGreen(1),
                    MarkerRed(2),
                    MarkerBlue(3),
                    XWhite(4),
                    TriangleRed(5),
                    SquareWhite(6),
                    MarkerSign(7),
                    MarkerPink(8),
                    MarkerOrange(9),
                    MarkerYellow(10),
                    MarkerTeal(11),
                    TriangleGreen(12),
                    SmallSquareWhite(13),
                    Mansion(14),
                    Monument(15),
                    NoDraw(16),
                    VillageDesert(17),
                    VillagePlains(18),
                    VillageSavanna(19),
                    VillageSnowy(20),
                    VillageTaiga(21),
                    JungleTemple(22),
                    WitchHut(23),
                    TrialChambers(24);

                    companion object : ProtoCodec<Type> {
                        val Player = MarkerWhite
                        val PlayerOffMap = SquareWhite
                        val PlayerOffLimits = SmallSquareWhite
                        val PlayerHidden = NoDraw
                        val ItemFrame = MarkerGreen

                        override fun serialize(value: Type, stream: Sink) {
                            Proto.Byte.serialize(value.ordinal.toByte(), stream)
                        }

                        override fun deserialize(stream: Source): Type {
                            return entries[Proto.Byte.deserialize(stream).toInt()]
                        }
                    }
                }

                override fun serialize(value: MapDecoration, stream: Sink) {
                    Type.serialize(value.type, stream)
                    Proto.Byte.serialize(value.rotation, stream)
                    Proto.Byte.serialize(value.x, stream)
                    Proto.Byte.serialize(value.y, stream)
                    Proto.String.serialize(value.label, stream)
                    IVarColorRGBA.serialize(value.color, stream)
                }

                override fun deserialize(stream: Source): MapDecoration {
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
                    Entity,
                    Block;

                    companion object : ProtoCodec<Type> {
                        override fun serialize(value: Type, stream: Sink) {
                            ProtoLE.Int.serialize(value.ordinal, stream)
                        }

                        override fun deserialize(stream: Source): Type {
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

                override fun serialize(value: MapItemTrackedActor, stream: Sink) {
                    Type.serialize(value.type, stream)
                    when (value.type) {
                        Type.Entity -> {
                            val entityData = value.data as EntityData
                            ActorUniqueID.serialize(entityData.uniqueID, stream)
                        }

                        Type.Block -> {
                            val blockData = value.data as BlockData
                            UIVector3.serialize(blockData.blockPosition, stream)
                        }
                    }
                }

                override fun deserialize(stream: Source): MapItemTrackedActor {
                    val type: Type
                    return MapItemTrackedActor(
                        type = Type.deserialize(stream).also { type = it },
                        data = when (type) {
                            Type.Entity -> EntityData(
                                uniqueID = ActorUniqueID.deserialize(stream)
                            )

                            Type.Block -> BlockData(
                                blockPosition = UIVector3.deserialize(stream)
                            )
                        }
                    )
                }
            }
        }

        override val id: Int = 67

        override fun deserialize(stream: Source): ClientboundMapItemDataPacket {
            val typeFlags: UInt
            return ClientboundMapItemDataPacket(
                mapID = ActorUniqueID.deserialize(stream),
                typeFlags = ProtoVAR.UInt.deserialize(stream).also { typeFlags = it },
                dimension = Proto.Byte.deserialize(stream),
                isLockedMap = Proto.Boolean.deserialize(stream),
                mapOrigin = IVector3.deserialize(stream),

                creationData = when {
                    typeFlags and Type.Creation.bit != 0u -> CreationData(
                        mapIDList = ProtoHelper.deserializeList(stream, ActorUniqueID)
                    )

                    else -> null
                },

                scale = when {
                    typeFlags and (Type.Creation.bit or Type.DecorationUpdate.bit or Type.TextureUpdate.bit) != 0u -> Proto.Byte.deserialize(
                        stream
                    )

                    else -> null
                },

                decorationUpdateData = when {
                    typeFlags and Type.DecorationUpdate.bit != 0u -> DecorationUpdateData(
                        actorIDs = ProtoHelper.deserializeList(stream, MapItemTrackedActor),
                        decorationList = ProtoHelper.deserializeList(stream, MapDecoration)
                    )

                    else -> null
                },

                textureUpdateData = when {
                    typeFlags and Type.TextureUpdate.bit != 0u -> TextureUpdateData(
                        textureWidth = ProtoVAR.Int.deserialize(stream),
                        textureHeight = ProtoVAR.Int.deserialize(stream),
                        xTexCoordinate = ProtoVAR.Int.deserialize(stream),
                        yTexCoordinate = ProtoVAR.Int.deserialize(stream),
                        pixels = ProtoHelper.deserializeList(stream, IVarColorRGBA)
                    )

                    else -> null
                }
            )
        }

        override fun serialize(value: ClientboundMapItemDataPacket, stream: Sink) {
            ActorUniqueID.serialize(value.mapID, stream)
            ProtoVAR.UInt.serialize(value.typeFlags, stream)
            Proto.Byte.serialize(value.dimension, stream)
            Proto.Boolean.serialize(value.isLockedMap, stream)
            IVector3.serialize(value.mapOrigin, stream)

            if (value.typeFlags and Type.Creation.bit != 0u) {
                val creationData = value.creationData as CreationData
                ProtoHelper.serializeList(creationData.mapIDList, stream, ActorUniqueID)
            }

            if (value.typeFlags and (Type.Creation.bit or Type.DecorationUpdate.bit or Type.TextureUpdate.bit) != 0u) {
                val scale = value.scale as Byte
                Proto.Byte.serialize(scale, stream)
            }

            if (value.typeFlags and Type.DecorationUpdate.bit != 0u) {
                val decorationUpdateData = value.decorationUpdateData as DecorationUpdateData
                ProtoHelper.serializeList(decorationUpdateData.actorIDs, stream, MapItemTrackedActor)
                ProtoHelper.serializeList(decorationUpdateData.decorationList, stream, MapDecoration)
            }

            if (value.typeFlags and Type.TextureUpdate.bit != 0u) {
                val textureUpdateData = value.textureUpdateData as TextureUpdateData
                ProtoVAR.Int.serialize(textureUpdateData.textureWidth, stream)
                ProtoVAR.Int.serialize(textureUpdateData.textureHeight, stream)
                ProtoVAR.Int.serialize(textureUpdateData.xTexCoordinate, stream)
                ProtoVAR.Int.serialize(textureUpdateData.yTexCoordinate, stream)
                ProtoHelper.serializeList(textureUpdateData.pixels, stream, IVarColorRGBA)
            }
        }
    }
}
