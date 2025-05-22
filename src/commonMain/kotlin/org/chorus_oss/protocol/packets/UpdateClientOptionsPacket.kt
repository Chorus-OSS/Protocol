package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte

data class UpdateClientOptionsPacket(
    val graphicsMode: GraphicsMode?,
) : Packet(id) {
    companion object : PacketCodec<UpdateClientOptionsPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class GraphicsMode {
            Simple,
            Fancy,
            Advanced,
            RayTraced;

            companion object : ProtoCodec<GraphicsMode> {
                override fun serialize(
                    value: GraphicsMode,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): GraphicsMode {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.UPDATE_CLIENT_OPTIONS_PACKET

        override fun serialize(
            value: UpdateClientOptionsPacket,
            stream: Sink
        ) {
            ProtoHelper.serializeNullable(value.graphicsMode, stream, GraphicsMode)
        }

        override fun deserialize(stream: Source): UpdateClientOptionsPacket {
            return UpdateClientOptionsPacket(
                graphicsMode = ProtoHelper.deserializeNullable(stream, GraphicsMode)
            )
        }
    }
}
