package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

class PlayerAbilitySet(
    private val flags: MutableSet<PlayerAbility> = mutableSetOf(),
) : MutableSet<PlayerAbility> by flags {
    companion object : ProtoCodec<PlayerAbilitySet> {
        override fun serialize(value: PlayerAbilitySet, stream: Sink) {
            var bitset = 0
            for (flag in value.flags) {
                bitset = bitset or (1 shl flag.ordinal)
            }
            ProtoLE.Int.serialize(bitset, stream)
        }

        override fun deserialize(stream: Source): PlayerAbilitySet {
            val bitset = ProtoLE.Int.deserialize(stream)
            val flags = mutableSetOf<PlayerAbility>()

            for (i in 0 until PlayerAbility.entries.size) {
                if (bitset and (1 shl i) != 0) {
                    flags.add(PlayerAbility.entries[i])
                }
            }
            return PlayerAbilitySet(flags)
        }
    }
}
