package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Short


data class SimpleEventPacket(
    val eventType: EventType
) : Packet(id) {
    companion object : PacketCodec<SimpleEventPacket> {
        enum class EventType(val net: Short) {
            CommandsEnabled(1),
            CommandsDisabled(2),
            UnlockWorldTemplateSettings(3);

            companion object : ProtoCodec<EventType> {
                override fun serialize(
                    value: EventType,
                    stream: Sink
                ) {
                    ProtoLE.Short.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): EventType {
                    return ProtoLE.Short.deserialize(stream).let {
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
