package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Int


data class ServerboundLoadingScreenPacket(
    val type: Type,
    val loadingScreenID: Int?,
) : Packet(id) {
    companion object : PacketCodec<ServerboundLoadingScreenPacket> {
        enum class Type {
            Unknown,
            StartLoadingScreen,
            EndLoadingScreen;

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[ProtoVAR.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int = 312

        override fun serialize(
            value: ServerboundLoadingScreenPacket,
            stream: Sink
        ) {
            Type.serialize(value.type, stream)
            ProtoHelper.serializeNullable(value.loadingScreenID, stream, ProtoLE.Int)
        }

        override fun deserialize(stream: Source): ServerboundLoadingScreenPacket {
            return ServerboundLoadingScreenPacket(
                type = Type.deserialize(stream),
                loadingScreenID = ProtoHelper.deserializeNullable(stream, ProtoLE.Int),
            )
        }
    }
}
