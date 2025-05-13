package org.chorus_oss.protocol.core

object PacketRegistry {
    private val registry = mutableMapOf<Int, PacketCodec<*>>()

    fun register(codec: PacketCodec<*>) {
        registry[codec.id] = codec
    }

    fun get(id: Int): PacketCodec<*>? = registry[id]

    @Suppress("UNCHECKED_CAST")
    fun <T : Packet> get(packet: T): PacketCodec<T>? = registry[packet.id] as? PacketCodec<T>?
}