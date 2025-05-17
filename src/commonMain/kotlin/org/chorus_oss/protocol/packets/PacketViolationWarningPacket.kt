package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String


data class PacketViolationWarningPacket(
    val type: Type,
    val severity: Severity,
    val packetID: Int,
    val violationContext: String,
) : Packet(id) {
    companion object : PacketCodec<PacketViolationWarningPacket> {
        enum class Type(val net: Int) {
            Unknown(-1),
            MalformedPacket(0);

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): Type {
                    return ProtoVAR.Int.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        enum class Severity(val net: Int) {
            Unknown(-1),
            Warning(0),
            FinalWarning(1),
            TerminatingConnection(2);

            companion object : ProtoCodec<Severity> {
                override fun serialize(
                    value: Severity,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): Severity {
                    return ProtoVAR.Int.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.PACKET_VIOLATION_WARNING_PACKET

        override fun serialize(
            value: PacketViolationWarningPacket,
            stream: Sink
        ) {
            Type.serialize(value.type, stream)
            Severity.serialize(value.severity, stream)
            ProtoVAR.Int.serialize(value.packetID, stream)
            Proto.String.serialize(value.violationContext, stream)
        }

        override fun deserialize(stream: Source): PacketViolationWarningPacket {
            return PacketViolationWarningPacket(
                type = Type.deserialize(stream),
                severity = Severity.deserialize(stream),
                packetID = ProtoVAR.Int.deserialize(stream),
                violationContext = Proto.String.deserialize(stream),
            )
        }
    }
}
