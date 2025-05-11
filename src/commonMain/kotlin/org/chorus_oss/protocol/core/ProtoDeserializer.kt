package org.chorus_oss.protocol.core

import kotlinx.io.Source

fun interface ProtoDeserializer<T> {
    fun deserialize(stream: Source): T
}