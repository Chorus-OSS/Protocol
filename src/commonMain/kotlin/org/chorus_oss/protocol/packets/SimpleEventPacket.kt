package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.UShort


data class SimpleEventPacket(
    val eventType: EventType
) : Packet(id) {
    companion object : PacketCodec<SimpleEventPacket> {
        enum class EventType(val net: UShort) {
            CommandsEnabled(1u),
            CommandsDisabled(2u),
            UnlockWorldTemplateSettings(3u);

            companion object : ProtoCodec<EventType> {
                override fun serialize(
                    value: EventType,
                    stream: Sink
                ) {
                    ProtoLE.UShort.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): EventType {
                    return ProtoLE.UShort.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        override val id: Int = 64

        override fun serialize(value: SimpleEventPacket, stream: Sink) {
            EventType.serialize(value.eventType, stream)
        }

        override fun deserialize(stream: Source): SimpleEventPacket {
            return SimpleEventPacket(
                eventType = EventType.deserialize(stream),
            )
        }
    }
}
