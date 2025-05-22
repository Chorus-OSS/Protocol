package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.scoreboard.ScoreboardSlot
import org.chorus_oss.protocol.types.scoreboard.ScoreboardSlotOrder


data class SetDisplayObjectivePacket(
    val displaySlot: ScoreboardSlot,
    val objectiveName: String,
    val displayName: String,
    val criteriaName: String,
    val sortOrder: ScoreboardSlotOrder,
) : Packet(id) {
    companion object : PacketCodec<SetDisplayObjectivePacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.SET_DISPLAY_OBJECTIVE_PACKET

        override fun serialize(
            value: SetDisplayObjectivePacket,
            stream: Sink
        ) {
            ScoreboardSlot.serialize(value.displaySlot, stream)
            Proto.String.serialize(value.objectiveName, stream)
            Proto.String.serialize(value.displayName, stream)
            Proto.String.serialize(value.criteriaName, stream)
            ScoreboardSlotOrder.serialize(value.sortOrder, stream)
        }

        override fun deserialize(stream: Source): SetDisplayObjectivePacket {
            return SetDisplayObjectivePacket(
                displaySlot = ScoreboardSlot.deserialize(stream),
                objectiveName = Proto.String.deserialize(stream),
                displayName = Proto.String.deserialize(stream),
                criteriaName = Proto.String.deserialize(stream),
                sortOrder = ScoreboardSlotOrder.deserialize(stream),
            )
        }
    }
}
