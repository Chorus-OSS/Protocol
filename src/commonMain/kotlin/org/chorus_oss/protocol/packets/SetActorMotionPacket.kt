package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f


data class SetActorMotionPacket(
    val actorRuntimeID: ActorRuntimeID,
    val motion: Vector3f,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<SetActorMotionPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.SET_ACTOR_MOTION_PACKET

        override fun serialize(value: SetActorMotionPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            Vector3f.serialize(value.motion, stream)
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): SetActorMotionPacket {
            return SetActorMotionPacket(
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                motion = Vector3f.deserialize(stream),
                tick = ProtoVAR.ULong.deserialize(stream),
            )
        }
    }
}
