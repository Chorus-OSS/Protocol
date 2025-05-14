package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.shared.types.Vector3f

data class ClientboundDebugRendererPacket(
    val type: Type,
    val data: Data? = null,
) : Packet(id) {
    companion object : PacketCodec<ClientboundDebugRendererPacket> {
        enum class Type {
            Clear,
            AddCube;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    ProtoLE.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[ProtoLE.UInt.deserialize(stream).toInt()]
                }
            }
        }

        interface Data
        data class AddCubeData(
            val text: String,
            val position: Vector3f,
            val red: Float,
            val green: Float,
            val blue: Float,
            val alpha: Float,
            val duration: ULong,
        ) : Data

        override val id: Int
            get() = ProtocolInfo.CLIENTBOUND_DEBUG_RENDERER_PACKET

        override fun deserialize(stream: Source): ClientboundDebugRendererPacket {
            val type: Type
            return ClientboundDebugRendererPacket(
                type = Type.deserialize(stream).also { type = it },
                data = when (type) {
                    Type.AddCube -> AddCubeData(
                        text = Proto.String.deserialize(stream),
                        position = Vector3f.deserialize(stream),
                        red = ProtoLE.Float.deserialize(stream),
                        green = ProtoLE.Float.deserialize(stream),
                        blue = ProtoLE.Float.deserialize(stream),
                        alpha = ProtoLE.Float.deserialize(stream),
                        duration = ProtoLE.ULong.deserialize(stream)
                    )
                    else -> null
                }
            )
        }

        override fun serialize(value: ClientboundDebugRendererPacket, stream: Sink) {
            Type.serialize(value.type, stream)
            when (value.type) {
                Type.AddCube -> {
                    val addCubeData = value.data as AddCubeData
                    Proto.String.serialize(addCubeData.text, stream)
                    Vector3f.serialize(addCubeData.position, stream)
                    ProtoLE.Float.serialize(addCubeData.red, stream)
                    ProtoLE.Float.serialize(addCubeData.green, stream)
                    ProtoLE.Float.serialize(addCubeData.blue, stream)
                    ProtoLE.Float.serialize(addCubeData.alpha, stream)
                    ProtoLE.ULong.serialize(addCubeData.duration, stream)
                }
                else -> Unit
            }
        }
    }
}
