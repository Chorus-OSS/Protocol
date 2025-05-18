package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.ExperimentData
import org.chorus_oss.protocol.types.StackResourcePack

class ResourcePackStackPacket(
    val texturePackRequired: Boolean,
    val behaviourPacks: List<StackResourcePack>,
    val texturePacks: List<StackResourcePack>,
    val baseGameVersion: String,
    val experiments: List<ExperimentData>,
    val experimentsPreviouslyToggled: Boolean,
    val includeEditorPacks: Boolean,
) : Packet(id) {
    companion object : PacketCodec<ResourcePackStackPacket> {
        override val id: Int
            get() = ProtocolInfo.RESOURCE_PACK_STACK_PACKET

        override fun serialize(
            value: ResourcePackStackPacket,
            stream: Sink
        ) {
            Proto.Boolean.serialize(value.texturePackRequired, stream)
            ProtoHelper.serializeList(value.behaviourPacks, stream, StackResourcePack)
            ProtoHelper.serializeList(value.texturePacks, stream, StackResourcePack)
            Proto.String.serialize(value.baseGameVersion, stream)
            value.experiments.let { experiments ->
                ProtoLE.UInt.serialize(experiments.size.toUInt(), stream)
                experiments.forEach { ExperimentData.serialize(it, stream) }
            }
            Proto.Boolean.serialize(value.experimentsPreviouslyToggled, stream)
            Proto.Boolean.serialize(value.includeEditorPacks, stream)
        }

        override fun deserialize(stream: Source): ResourcePackStackPacket {
            return ResourcePackStackPacket(
                texturePackRequired = Proto.Boolean.deserialize(stream),
                behaviourPacks = ProtoHelper.deserializeList(stream, StackResourcePack),
                texturePacks = ProtoHelper.deserializeList(stream, StackResourcePack),
                baseGameVersion = Proto.String.deserialize(stream),
                experiments = List(ProtoLE.UInt.deserialize(stream).toInt()) {
                    ExperimentData.deserialize(stream)
                },
                experimentsPreviouslyToggled = Proto.Boolean.deserialize(stream),
                includeEditorPacks = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
