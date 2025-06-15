package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float


data class ServerboundDiagnosticsPacket(
    val avgFps: Float,
    val avgServerSimTickTimeMS: Float,
    val avgClientSimTickTimeMS: Float,
    val avgBeginFrameTimeMS: Float,
    val avgInputTimeMS: Float,
    val avgRenderTimeMS: Float,
    val avgEndFrameTimeMS: Float,
    val avgRemainderTimePercent: Float,
    val avgUnaccountedTimePercent: Float,
) : Packet(id) {
    companion object : PacketCodec<ServerboundDiagnosticsPacket> {
        override val id: Int = 315

        override fun serialize(
            value: ServerboundDiagnosticsPacket,
            stream: Sink
        ) {
            ProtoLE.Float.serialize(value.avgFps, stream)
            ProtoLE.Float.serialize(value.avgServerSimTickTimeMS, stream)
            ProtoLE.Float.serialize(value.avgClientSimTickTimeMS, stream)
            ProtoLE.Float.serialize(value.avgBeginFrameTimeMS, stream)
            ProtoLE.Float.serialize(value.avgInputTimeMS, stream)
            ProtoLE.Float.serialize(value.avgRenderTimeMS, stream)
            ProtoLE.Float.serialize(value.avgEndFrameTimeMS, stream)
            ProtoLE.Float.serialize(value.avgRemainderTimePercent, stream)
            ProtoLE.Float.serialize(value.avgUnaccountedTimePercent, stream)
        }

        override fun deserialize(stream: Source): ServerboundDiagnosticsPacket {
            return ServerboundDiagnosticsPacket(
                avgFps = ProtoLE.Float.deserialize(stream),
                avgServerSimTickTimeMS = ProtoLE.Float.deserialize(stream),
                avgClientSimTickTimeMS = ProtoLE.Float.deserialize(stream),
                avgBeginFrameTimeMS = ProtoLE.Float.deserialize(stream),
                avgInputTimeMS = ProtoLE.Float.deserialize(stream),
                avgRenderTimeMS = ProtoLE.Float.deserialize(stream),
                avgEndFrameTimeMS = ProtoLE.Float.deserialize(stream),
                avgRemainderTimePercent = ProtoLE.Float.deserialize(stream),
                avgUnaccountedTimePercent = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
