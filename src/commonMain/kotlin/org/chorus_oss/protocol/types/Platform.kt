package org.chorus_oss.protocol.types

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

enum class Platform(val platformName: String, val id: Int) {
    UNKNOWN("Unknown", -1),
    ANDROID("Android", 1),
    IOS("iOS", 2),
    MAC_OS("macOS", 3),
    FIRE_OS("FireOS", 4),
    GEAR_VR("Gear VR", 5),
    HOLOLENS("Hololens", 6),
    WINDOWS_10("Windows", 7),
    WINDOWS("Windows", 8),
    DEDICATED("Dedicated", 9),

    @Deprecated("tvOS_Deprecated")
    TVOS("TVOS", 10),
    PLAYSTATION("PlayStation", 11),
    SWITCH("Switch", 12),
    XBOX_ONE("Xbox One", 13),

    @Deprecated("WindowsPhone_Deprecated")
    WINDOWS_PHONE("Windows Phone", 14),
    LINUX("Linux", 15);


    companion object : ProtoCodec<Platform> {
        override fun serialize(value: Platform, stream: Buffer) {
            ProtoLE.Int.serialize(value.id, stream)
        }

        override fun deserialize(stream: Buffer): Platform {
            val id = ProtoLE.Int.deserialize(stream)
            return entries.find { it.id == id }!!
        }
    }
}