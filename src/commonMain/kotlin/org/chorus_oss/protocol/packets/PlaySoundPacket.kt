package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3
import org.chorus_oss.protocol.types.Vector3f


data class PlaySoundPacket(
    val soundName: String,
    val position: Vector3f,
    val volume: Float,
    val pitch: Float,
) : Packet(id) {
    companion object : PacketCodec<PlaySoundPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.PLAY_SOUND_PACKET

        override fun serialize(value: PlaySoundPacket, stream: Sink) {
            Proto.String.serialize(value.soundName, stream)
            UIVector3.serialize(
                IVector3(
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
                position = UIVector3.deserialize(stream).let {
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
