package org.chorus_oss.protocol.core

import kotlinx.io.Sink

fun interface ProtoSerializer<T> {
    fun serialize(value: T, stream: Sink)
}