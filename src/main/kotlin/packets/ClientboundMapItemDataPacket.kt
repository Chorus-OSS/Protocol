package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3
import org.chorus_oss.chorus.math.Vector3

import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.Rotation
import java.awt.Color

data class ClientboundMapItemDataPacket(
    val mapID: ActorUniqueID,
    val typeFlags: Set<Type>,
    val dimension: Byte,
    val isLockedMap: Boolean,
    val mapOrigin: BlockVector3,
    val scale: Byte? = null,
    val creationData: CreationData? = null,
    val decorationUpdateData: DecorationUpdateData? = null,
    val textureUpdateData: TextureUpdateData? = null,
) : DataPacket(), PacketEncoder {
    enum class Type(val bit: Int) {
        INVALID(0),
        TEXTURE_UPDATE(1 shl 1),
        DECORATION_UPDATE(1 shl 2),
        CREATION(1 shl 3);
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
        val pixels: List<Int>
    )

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.mapID)
        byteBuf.writeUnsignedVarInt(run {
            var bitField = 0
            this.typeFlags.forEach {
                bitField = bitField or it.bit
            }
            bitField
        })
        byteBuf.writeByte(this.dimension.toInt())
        byteBuf.writeBoolean(this.isLockedMap)
        byteBuf.writeBlockVector3(this.mapOrigin)

        if (this.typeFlags.contains(Type.CREATION)) {
            val creationData = this.creationData as CreationData

            byteBuf.writeArray(creationData.mapIDList) { buf, id ->
                buf.writeActorUniqueID(id)
            }
        }

        if (this.typeFlags.any { it in setOf(Type.CREATION, Type.TEXTURE_UPDATE, Type.DECORATION_UPDATE) }) {
            val scale = this.scale as Byte

            byteBuf.writeByte(scale.toInt())
        }

        if (this.typeFlags.contains(Type.DECORATION_UPDATE)) {
            val decorationUpdateData = this.decorationUpdateData as DecorationUpdateData

            byteBuf.writeArray(decorationUpdateData.actorIDs) { buf, value ->
                buf.writeIntLE(value.type.ordinal)
                when (value.type) {
                    MapItemTrackedActor.Type.BLOCK -> {
                        val data = value.data as MapItemTrackedActor.BlockData

                        buf.writeBlockVector3(data.blockPosition)
                    }

                    MapItemTrackedActor.Type.ENTITY -> {
                        val data = value.data as MapItemTrackedActor.EntityData

                        buf.writeActorUniqueID(data.uniqueID)
                    }
                }
            }

            byteBuf.writeArray(decorationUpdateData.decorationList) { buf, value ->
                buf.writeByte(value.type.id.toInt())
                buf.writeByte(value.rotation.id.toInt())
                buf.writeByte(value.x.toInt())
                buf.writeByte(value.y.toInt())
                buf.writeString(value.label)
                buf.writeUnsignedVarInt(value.color.rgb)
            }
        }

        if (this.typeFlags.contains(Type.TEXTURE_UPDATE)) {
            val textureUpdateData = this.textureUpdateData as TextureUpdateData

            byteBuf.writeVarInt(textureUpdateData.textureWidth)
            byteBuf.writeVarInt(textureUpdateData.textureHeight)
            byteBuf.writeVarInt(textureUpdateData.xTexCoordinate)
            byteBuf.writeVarInt(textureUpdateData.yTexCoordinate)

            byteBuf.writeArray(textureUpdateData.pixels) { buf, pixel ->
                buf.writeUnsignedVarInt(pixel)
            }
        }
    }

    class MapDecoration(
        val type: Type,
        val rotation: Rotation,
        val x: Byte,
        val y: Byte,
        val label: String,
        val color: Color,
    ) {
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

            companion object {
                val PLAYER = MARKER_WHITE
                val PLAYER_OFF_MAP = SQUARE_WHITE
                val PLAYER_OFF_LIMITS = SMALL_SQUARE_WHITE
                val PLAYER_HIDDEN = NO_DRAW
                val ITEM_FRAME = MARKER_GREEN
            }
        }
    }


    class MapItemTrackedActor(
        val type: Type,
        val data: Data,
    ) {
        constructor(entityId: Long) : this(
            Type.ENTITY,
            EntityData(
                uniqueID = entityId
            )
        )

        constructor(position: Vector3) : this(
            Type.BLOCK,
            BlockData(
                blockPosition = position.asBlockVector3()
            )
        )

        enum class Type {
            ENTITY,
            BLOCK
        }

        interface Data
        data class EntityData(
            val uniqueID: ActorUniqueID
        ) : Data

        data class BlockData(
            val blockPosition: BlockVector3,
        ) : Data
    }

    override fun pid(): Int {
        return ProtocolInfo.CLIENTBOUND_MAP_ITEM_DATA_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
