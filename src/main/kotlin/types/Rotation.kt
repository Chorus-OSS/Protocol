package org.chorus_oss.protocol.types

enum class Rotation(val id: Byte) {
    NONE(0),
    ROTATE_90(1),
    ROTATE_180(2),
    ROTATE_270(3);

    companion object {
        val CLOCKWISE_90 = ROTATE_90
        val CLOCKWISE_180 = ROTATE_180
        val COUNTER_CLOCKWISE_90 = ROTATE_270
    }
}