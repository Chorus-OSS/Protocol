package org.chorus_oss.protocol.types


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte

data class SerializedAbilitiesData(
    val targetPlayerRawID: ActorUniqueID,
    val playerPermissions: PlayerPermission,
    val commandPermissions: CommandPermission,
    val layers: List<AbilityLayer>
) {
    companion object : ProtoCodec<SerializedAbilitiesData> {
        override fun serialize(value: SerializedAbilitiesData, stream: Sink) {
            ActorUniqueID.serialize(value.targetPlayerRawID, stream)
            Proto.Byte.serialize(value.playerPermissions.ordinal.toByte(), stream)
            Proto.Byte.serialize(value.commandPermissions.ordinal.toByte(), stream)
            ProtoHelper.serializeList(value.layers, stream, AbilityLayer::serialize)
        }

        override fun deserialize(stream: Source): SerializedAbilitiesData {
            return SerializedAbilitiesData(
                targetPlayerRawID = ActorUniqueID.deserialize(stream),
                playerPermissions = PlayerPermission.entries[Proto.Byte.deserialize(stream).toInt()],
                commandPermissions = CommandPermission.entries[Proto.Byte.deserialize(stream).toInt()],
                layers = ProtoHelper.deserializeList(stream, AbilityLayer::deserialize)
            )
        }
    }
}
