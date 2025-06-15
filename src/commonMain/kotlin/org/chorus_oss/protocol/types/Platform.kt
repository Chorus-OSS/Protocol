package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

enum class Platform(val platformName: String, val id: Int) {
    Unknown("Unknown", -1),
    Android("Android", 1),
    IOS("iOS", 2),
    MacOS("macOS", 3),
    FireOS("FireOS", 4),
    GearVR("Gear VR", 5),
    Hololens("Hololens", 6),
    Windows10("Windows", 7),
    Windows("Windows", 8),
    Dedicated("Dedicated", 9),

    @Deprecated("tvOS_Deprecated")
    TvOS("TVOS", 10),
    PlayStation("PlayStation", 11),
    Switch("Switch", 12),
    XboxOne("Xbox One", 13),

    @Deprecated("WindowsPhone_Deprecated")
    WindowsPhone("Windows Phone", 14),
    Linux("Linux", 15);


    companion object : ProtoCodec<Platform> {
        override fun serialize(value: Platform, stream: Sink) {
            ProtoLE.Int.serialize(value.id, stream)
        }

        override fun deserialize(stream: Source): Platform {
            return ProtoLE.Int.deserialize(stream).let {
                entries.find { e -> e.id == it }!!
            }
        }
    }
}