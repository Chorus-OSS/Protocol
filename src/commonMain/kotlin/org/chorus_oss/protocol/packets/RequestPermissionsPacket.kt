package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.PlayerPermission


data class RequestPermissionsPacket(
    val entityUniqueID: ActorUniqueID,
    val permissionLevel: PlayerPermission,
    val requestedPermissions: UShort,
) : Packet(id) {
    companion object : PacketCodec<RequestPermissionsPacket> {
        override val id: Int = 185

        override fun serialize(
            value: RequestPermissionsPacket,
            stream: Sink
        ) {
            ActorUniqueID.serialize(value.entityUniqueID, stream)
            PlayerPermission.serialize(value.permissionLevel, stream)
            ProtoLE.UShort.serialize(value.requestedPermissions, stream)
        }

        override fun deserialize(stream: Source): RequestPermissionsPacket {
            return RequestPermissionsPacket(
                entityUniqueID = ActorUniqueID.deserialize(stream),
                permissionLevel = PlayerPermission.deserialize(stream),
                requestedPermissions = ProtoLE.UShort.deserialize(stream),
            )
        }
    }
}
