package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.UInt

data class OnScreenTextureAnimationPacket(
    val animationType: UInt,
) : Packet(id) {
    companion object : PacketCodec<OnScreenTextureAnimationPacket> {
        override val id: Int = 130

        override fun serialize(
            value: OnScreenTextureAnimationPacket,
            stream: Sink
        ) {
            ProtoLE.UInt.serialize(value.animationType, stream)
        }

        override fun deserialize(stream: Source): OnScreenTextureAnimationPacket {
            return OnScreenTextureAnimationPacket(
                animationType = ProtoLE.UInt.deserialize(stream),
            )
        }
    }
}
