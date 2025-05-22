package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Int


data class PlayStatusPacket(
    val status: Status
) : Packet(id) {
    companion object : PacketCodec<PlayStatusPacket> {
        init { PacketRegistry.register(this) }

        enum class Status {
            LoginSuccess,
            LoginFailedClient,
            LoginFailedServer,
            PlayerSpawn,
            LoginFailedInvalidTenant,
            LoginFailedVanillaToEduMismatch,
            LoginFailedEduToVanillaMismatch,
            LoginFailedServerFull,
            LoginFailedEditorToVanillaMismatch,
            LoginFailedVanillaToEditorMismatch;

            companion object : ProtoCodec<Status> {
                override fun serialize(
                    value: Status,
                    stream: Sink
                ) {
                    ProtoBE.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): Status {
                    return entries[ProtoBE.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.PLAY_STATUS_PACKET

        override fun serialize(value: PlayStatusPacket, stream: Sink) {
            Status.serialize(value.status, stream)
        }

        override fun deserialize(stream: Source): PlayStatusPacket {
            return PlayStatusPacket(
                status = Status.deserialize(stream),
            )
        }
    }
}
