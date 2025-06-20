package org.chorus_oss.protocol.types.structure

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos
import org.chorus_oss.protocol.types.Vector3f

data class StructureSettings(
    val paletteName: String,
    val ignoringEntities: Boolean,
    val ignoringBlocks: Boolean,
    val allowNonTickingChunks: Boolean,
    val size: BlockPos,
    val offset: BlockPos,
    val lastEditedPlayerUniqueID: Long,
    val rotation: StructureRotation,
    val mirror: StructureMirror,
    val animationMode: StructureAnimationMode,
    val animationDuration: Float,
    val integrity: Float,
    val seed: UInt,
    val pivot: Vector3f,
) {
    companion object : ProtoCodec<StructureSettings> {
        override fun serialize(
            value: StructureSettings,
            stream: Sink
        ) {
            Proto.String.serialize(value.paletteName, stream)
            Proto.Boolean.serialize(value.ignoringEntities, stream)
            Proto.Boolean.serialize(value.ignoringBlocks, stream)
            Proto.Boolean.serialize(value.allowNonTickingChunks, stream)
            NetBlockPos.serialize(value.size, stream)
            NetBlockPos.serialize(value.offset, stream)
            ActorUniqueID.serialize(value.lastEditedPlayerUniqueID, stream)
            StructureRotation.serialize(value.rotation, stream)
            StructureMirror.serialize(value.mirror, stream)
            StructureAnimationMode.serialize(value.animationMode, stream)
            ProtoLE.Float.serialize(value.animationDuration, stream)
            ProtoLE.Float.serialize(value.integrity, stream)
            ProtoLE.UInt.serialize(value.seed, stream)
            Vector3f.serialize(value.pivot, stream)
        }

        override fun deserialize(stream: Source): StructureSettings {
            return StructureSettings(
                paletteName = Proto.String.deserialize(stream),
                ignoringEntities = Proto.Boolean.deserialize(stream),
                ignoringBlocks = Proto.Boolean.deserialize(stream),
                allowNonTickingChunks = Proto.Boolean.deserialize(stream),
                size = NetBlockPos.deserialize(stream),
                offset = NetBlockPos.deserialize(stream),
                lastEditedPlayerUniqueID = ActorUniqueID.deserialize(stream),
                rotation = StructureRotation.deserialize(stream),
                mirror = StructureMirror.deserialize(stream),
                animationMode = StructureAnimationMode.deserialize(stream),
                animationDuration = ProtoLE.Float.deserialize(stream),
                integrity = ProtoLE.Float.deserialize(stream),
                seed = ProtoLE.UInt.deserialize(stream),
                pivot = Vector3f.deserialize(stream),
            )
        }
    }
}