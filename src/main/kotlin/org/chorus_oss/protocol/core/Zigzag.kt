package org.chorus_oss.protocol.core

fun interface Zigzag<T, V> {
    operator fun invoke(value: T): V
}