package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec

data class BitSet(
    private val bits: List<Boolean>
) : List<Boolean> by bits {
    companion object : ProtoCodec<BitSet> {
        override fun serialize(value: BitSet, stream: Sink) {
            var byte: UByte = 0u
            var shift = 0

            if (value.isEmpty()) {
                Proto.UByte.serialize(0u, stream)
                return
            }

            for (i in value.indices) {
                val bit = value[i]
                if (bit) {
                    byte = byte or (1u shl shift).toUByte()
                }

                shift++

                if (shift == 7 || i == value.size - 1) {
                    if (i < value.size - 1) {
                        byte = byte or 0x80u
                    }

                    Proto.UByte.serialize(byte, stream)
                    byte = 0u
                    shift = 0
                }
            }
        }

        override fun deserialize(stream: Source): BitSet {
            val bits = mutableListOf<Boolean>()

            while (true) {
                val byte = Proto.UByte.deserialize(stream)

                for (i in 0 until 7) {
                    bits.add((byte and (1u shl i).toUByte()) != 0u.toUByte())
                }

                if (byte and 0x80u.toUByte() == 0u.toUByte()) {
                    break
                }
            }

            return BitSet(bits)
        }
    }
}
