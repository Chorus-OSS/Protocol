package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3
import org.chorus_oss.protocol.types.structure.StructureBlockType
import org.chorus_oss.protocol.types.structure.StructureRedstoneSaveMode
import org.chorus_oss.protocol.types.structure.StructureSettings


data class StructureBlockUpdatePacket(
    val position: IVector3,
    val structureName: String,
    val filteredStructureName: String,
    val dataField: String,
    val includePlayers: Boolean,
    val showBoundingBox: Boolean,
    val structureBlockType: StructureBlockType,
    val settings: StructureSettings,
    val redstoneSaveMode: StructureRedstoneSaveMode,
    val shouldTrigger: Boolean,
    val waterLogged: Boolean,
) : Packet(id) {
    companion object : PacketCodec<StructureBlockUpdatePacket> {
        override val id: Int
            get() = ProtocolInfo.STRUCTURE_BLOCK_UPDATE_PACKET

        override fun serialize(
            value: StructureBlockUpdatePacket,
            stream: Sink
        ) {
            UIVector3.serialize(value.position, stream)
            Proto.String.serialize(value.structureName, stream)
            Proto.String.serialize(value.filteredStructureName, stream)
            Proto.String.serialize(value.dataField, stream)
            Proto.Boolean.serialize(value.includePlayers, stream)
            Proto.Boolean.serialize(value.showBoundingBox, stream)
            StructureBlockType.serialize(value.structureBlockType, stream)
            StructureSettings.serialize(value.settings, stream)
            StructureRedstoneSaveMode.serialize(value.redstoneSaveMode, stream)
            Proto.Boolean.serialize(value.shouldTrigger, stream)
            Proto.Boolean.serialize(value.waterLogged, stream)
        }

        override fun deserialize(stream: Source): StructureBlockUpdatePacket {
            return StructureBlockUpdatePacket(
                position = UIVector3.deserialize(stream),
                structureName = Proto.String.deserialize(stream),
                filteredStructureName = Proto.String.deserialize(stream),
                dataField = Proto.String.deserialize(stream),
                includePlayers = Proto.Boolean.deserialize(stream),
                showBoundingBox = Proto.Boolean.deserialize(stream),
                structureBlockType = StructureBlockType.deserialize(stream),
                settings = StructureSettings.deserialize(stream),
                redstoneSaveMode = StructureRedstoneSaveMode.deserialize(stream),
                shouldTrigger = Proto.Boolean.deserialize(stream),
                waterLogged = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
