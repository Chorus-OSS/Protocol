package org.chorus_oss.protocol.types

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


    companion object {
        private val PLATFORM_BY_ID: Map<Int, Platform> = entries.associateBy { it.id }

        fun getPlatformByID(id: Int): Platform {
            return PLATFORM_BY_ID[id] ?: UNKNOWN
        }

        var VALUES: Array<Platform> = entries.toTypedArray()
    }
}