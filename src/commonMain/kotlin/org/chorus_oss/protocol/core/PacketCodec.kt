package org.chorus_oss.protocol.core

interface PacketCodec<T> : ProtoCodec<T> {
    val id: Int
}