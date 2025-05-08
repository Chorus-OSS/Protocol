package org.chorus_oss.protocol.core

import kotlinx.io.Buffer

fun interface ProtoDeserializer<T> {
    fun deserialize(stream: Buffer): T
}