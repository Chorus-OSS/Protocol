package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int

data class PositionTrackingDBServerBroadcastPacket(
    val broadcastAction: Action,
    val trackingID: Int,
    val payload: CompoundTag,
) : Packet(id) {
    companion object : PacketCodec<PositionTrackingDBServerBroadcastPacket> {
        init { PacketRegistry.register(this) }

        enum class Action {
            Update,
            Destroy,
            NotFound;

            companion object : ProtoCodec<Action> {
                override fun serialize(
                    value: Action,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Action {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.POSITION_TRACKING_DB_SERVER_BROADCAST_PACKET

        override fun serialize(
            value: PositionTrackingDBServerBroadcastPacket,
            stream: Sink
        ) {
            Action.serialize(value.broadcastAction, stream)
            ProtoVAR.Int.serialize(value.trackingID, stream)
            Tag.serialize(value.payload, stream, TagSerialization.NetLE, true)
        }

        override fun deserialize(stream: Source): PositionTrackingDBServerBroadcastPacket {
            return PositionTrackingDBServerBroadcastPacket(
                broadcastAction = Action.deserialize(stream),
                trackingID = ProtoVAR.Int.deserialize(stream),
                payload = Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag,
            )
        }
    }
}
