package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int


data class OnScreenTextureAnimationPacket(
    val animationType: Int,
) : Packet(id) {
    companion object : PacketCodec<OnScreenTextureAnimationPacket> {
        override val id: Int = 130

        override fun serialize(
            value: OnScreenTextureAnimationPacket,
            stream: Sink
        ) {
            ProtoLE.Int.serialize(value.animationType, stream)
        }

        override fun deserialize(stream: Source): OnScreenTextureAnimationPacket {
            return OnScreenTextureAnimationPacket(
                animationType = ProtoLE.Int.deserialize(stream),
            )
        }
    }
}
