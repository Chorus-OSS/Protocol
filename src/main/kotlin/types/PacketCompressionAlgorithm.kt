package org.chorus_oss.protocol.types

enum class PacketCompressionAlgorithm : CompressionAlgorithm {
    ZLIB,
    SNAPPY,
    NONE
}