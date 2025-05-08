package org.chorus_oss.protocol.core

import kotlinx.io.Buffer

fun interface ProtoSerializer<T> {
    fun serialize(value: T, stream: Buffer)
}