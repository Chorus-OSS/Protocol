package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos
import org.chorus_oss.protocol.types.Vector3f


data class PlaySoundPacket(
    val soundName: String,
    val position: Vector3f,
    val volume: Float,
    val pitch: Float,
) : Packet(id) {
    companion object : PacketCodec<PlaySoundPacket> {
        override val id: Int = 86

        override fun serialize(value: PlaySoundPacket, stream: Sink) {
            Proto.String.serialize(value.soundName, stream)
            NetBlockPos.serialize(
                BlockPos(
                    x = (value.position.x * 8f).toInt(),
                    y = (value.position.y * 8f).toInt(),
                    z = (value.position.z * 8f).toInt(),
                ), stream
            )
            ProtoLE.Float.serialize(value.volume, stream)
            ProtoLE.Float.serialize(value.pitch, stream)
        }

        override fun deserialize(stream: Source): PlaySoundPacket {
            return PlaySoundPacket(
                soundName = Proto.String.deserialize(stream),
                position = NetBlockPos.deserialize(stream).let {
                    Vector3f(
                        x = it.x.toFloat() / 8f,
                        y = it.y.toFloat() / 8f,
                        z = it.z.toFloat() / 8f,
                    )
                },
                volume = ProtoLE.Float.deserialize(stream),
                pitch = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
