package org.chorus_oss.protocol.types


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte

data class AbilitiesData(
    val targetPlayerRawID: ActorUniqueID,
    val playerPermissions: PlayerPermission,
    val commandPermissions: CommandPermission,
    val layers: List<AbilityLayer>
) {
    companion object : ProtoCodec<AbilitiesData> {
        override fun serialize(value: AbilitiesData, stream: Sink) {
            ActorUniqueID.serialize(value.targetPlayerRawID, stream)
            Proto.Byte.serialize(value.playerPermissions.ordinal.toByte(), stream)
            Proto.Byte.serialize(value.commandPermissions.ordinal.toByte(), stream)
            ProtoHelper.serializeList(value.layers, stream, AbilityLayer)
        }

        override fun deserialize(stream: Source): AbilitiesData {
            return AbilitiesData(
                targetPlayerRawID = ActorUniqueID.deserialize(stream),
                playerPermissions = PlayerPermission.entries[Proto.Byte.deserialize(stream).toInt()],
                commandPermissions = CommandPermission.entries[Proto.Byte.deserialize(stream).toInt()],
                layers = ProtoHelper.deserializeList(stream, AbilityLayer)
            )
        }
    }
}
