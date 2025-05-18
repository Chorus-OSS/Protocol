package org.chorus_oss.protocol.types.scoreboard

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

enum class ScoreboardSlot(val id: String) {
    List("list"),
    Sidebar("sidebar"),
    BelowName("belowname");

    companion object : ProtoCodec<ScoreboardSlot> {
        override fun serialize(
            value: ScoreboardSlot,
            stream: Sink
        ) {
            Proto.String.serialize(value.id, stream)
        }

        override fun deserialize(stream: Source): ScoreboardSlot {
            return Proto.String.deserialize(stream).let {
                entries.find { e -> e.id == it }!!
            }
        }
    }
}