package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.block.property.enums.StructureBlockType
import org.chorus_oss.chorus.math.BlockVector3

import org.chorus_oss.protocol.types.*


class StructureBlockUpdatePacket : Packet(id) {
    lateinit var blockPosition: BlockVector3
    lateinit var editorData: StructureEditorData
    var powered: Boolean = false
    var waterlogged: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBlockVector3(blockPosition)
        this.writeEditorData(byteBuf, editorData)
        byteBuf.writeBoolean(powered)
        byteBuf.writeBoolean(waterlogged)
    }

    private fun readEditorData(byteBuf: ByteBuf): StructureEditorData {
        val name = Proto.String.deserialize(stream)
        val filteredName = Proto.String.deserialize(stream)
        val dataField = Proto.String.deserialize(stream)
        val isIncludingPlayers = Proto.Boolean.deserialize(stream)
        val isBoundingBoxVisible = Proto.Boolean.deserialize(stream)
        val type = byteBuf.readVarInt()
        val structureSettings = readStructureSettings(byteBuf)
        val redstoneSaveMode = byteBuf.readVarInt()
        return StructureEditorData(
            name,
            filteredName,
            dataField,
            isIncludingPlayers,
            isBoundingBoxVisible,
            StructureBlockType.from(type),
            structureSettings,
            StructureRedstoneSaveMode.from(redstoneSaveMode)
        )
    }

    private fun readStructureSettings(byteBuf: ByteBuf): StructureSettings {
        val paletteName = Proto.String.deserialize(stream)
        val isIgnoringEntities = Proto.Boolean.deserialize(stream)
        val isIgnoringBlocks = Proto.Boolean.deserialize(stream)
        val isNonTickingPlayersAndTickingAreasEnabled = Proto.Boolean.deserialize(stream)
        val size = byteBuf.readBlockVector3()
        val offset = byteBuf.readBlockVector3()
        val lastEditedByEntityId = byteBuf.readVarLong()
        val rotation = Proto.Byte.deserialize(stream)
        val mirror = Proto.Byte.deserialize(stream)
        val animationMode = Proto.Byte.deserialize(stream)
        val animationSeconds = byteBuf.readFloatLE()
        val integrityValue = byteBuf.readFloatLE()
        val integritySeed = byteBuf.readIntLE()
        val pivot = Vector3f.deserialize(stream)
        return StructureSettings(
            paletteName,
            isIgnoringEntities,
            isIgnoringBlocks,
            isNonTickingPlayersAndTickingAreasEnabled,
            size,
            offset,
            lastEditedByEntityId,
            StructureRotation.from(rotation.toInt()),
            StructureMirror.from(mirror.toInt()),
            StructureAnimationMode.from(animationMode.toInt()),
            animationSeconds,
            integrityValue,
            integritySeed,
            pivot
        )
    }

    private fun writeEditorData(byteBuf: ByteBuf, editorData: StructureEditorData) {
        byteBuf.writeString(editorData.name)
        byteBuf.writeString(editorData.filteredName)
        byteBuf.writeString(editorData.dataField)
        byteBuf.writeBoolean(editorData.includingPlayers)
        byteBuf.writeBoolean(editorData.boundingBoxVisible)
        byteBuf.writeVarInt(editorData.type.ordinal)
        writeStructureSettings(byteBuf, editorData.settings)
        byteBuf.writeVarInt(editorData.redstoneSaveMode.ordinal)
    }

    private fun writeStructureSettings(byteBuf: ByteBuf, settings: StructureSettings) {
        byteBuf.writeString(settings.paletteName)
        byteBuf.writeBoolean(settings.ignoringEntities)
        byteBuf.writeBoolean(settings.ignoringBlocks)
        byteBuf.writeBoolean(settings.nonTickingPlayersAndTickingAreasEnabled)
        byteBuf.writeBlockVector3(settings.size)
        byteBuf.writeBlockVector3(settings.offset)
        byteBuf.writeVarLong(settings.lastEditedByEntityId)
        byteBuf.writeByte(settings.rotation.ordinal)
        byteBuf.writeByte(settings.mirror.ordinal)
        byteBuf.writeByte(settings.animationMode.ordinal)
        byteBuf.writeFloatLE(settings.animationSeconds)
        byteBuf.writeFloatLE(settings.integrityValue)
        byteBuf.writeIntLE(settings.integritySeed)
        byteBuf.writeVector3f(settings.pivot)
    }

    override fun pid(): Int {
        return ProtocolInfo.STRUCTURE_BLOCK_UPDATE_PACKET
    }



    companion object : PacketCodec<StructureBlockUpdatePacket> {
        override fun deserialize(stream: Source): StructureBlockUpdatePacket {
            val packet = StructureBlockUpdatePacket()

            packet.blockPosition = byteBuf.readBlockVector3()
            packet.editorData = packet.readEditorData(byteBuf)
            packet.powered = Proto.Boolean.deserialize(stream)
            packet.waterlogged = Proto.Boolean.deserialize(stream)

            return packet
        }
    }
}
