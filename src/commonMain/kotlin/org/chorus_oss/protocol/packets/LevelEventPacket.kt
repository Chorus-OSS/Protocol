package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.Vector3f

data class LevelEventPacket(
    val eventType: Int,
    val position: Vector3f,
    val eventData: Int,
) : Packet(id) {
    companion object : PacketCodec<LevelEventPacket> {
        init { PacketRegistry.register(this) }

        const val EVENT_UNDEFINED: Int = 0
        const val EVENT_SOUND_CLICK: Int = 1000
        const val EVENT_SOUND_CLICK_FAIL: Int = 1001
        const val EVENT_SOUND_LAUNCH: Int = 1002
        const val EVENT_SOUND_DOOR_OPEN: Int = 1003
        const val EVENT_SOUND_FIZZ: Int = 1004
        const val EVENT_SOUND_FUSE: Int = 1005
        const val EVENT_SOUND_PLAY_RECORDING: Int = 1006
        const val EVENT_SOUND_GHAST_WARNING: Int = 1007
        const val EVENT_SOUND_GHAST_FIREBALL: Int = 1008
        const val EVENT_SOUND_BLAZE_FIREBALL: Int = 1009
        const val EVENT_SOUND_ZOMBIE_DOOR_BUMP: Int = 1010
        const val EVENT_SOUND_ZOMBIE_DOOR_CRASH: Int = 1012
        const val EVENT_SOUND_ZOMBIE_INFECTED: Int = 1016
        const val EVENT_SOUND_ZOMBIE_CONVERTED: Int = 1017
        const val EVENT_SOUND_ENDERMAN_TELEPORT: Int = 1018
        const val EVENT_SOUND_ANVIL_BROKEN: Int = 1020
        const val EVENT_SOUND_ANVIL_USED: Int = 1021
        const val EVENT_SOUND_ANVIL_LAND: Int = 1022
        const val EVENT_SOUND_INFINITY_ARROW_PICKUP: Int = 1030
        const val EVENT_SOUND_TELEPORT_ENDERPEARL: Int = 1032
        const val EVENT_SOUND_ITEMFRAME_ITEM_ADD: Int = 1040
        const val EVENT_SOUND_ITEMFRAME_BREAK: Int = 1041
        const val EVENT_SOUND_ITEMFRAME_PLACE: Int = 1042
        const val EVENT_SOUND_ITEMFRAME_ITEM_REMOVE: Int = 1043
        const val EVENT_SOUND_ITEMFRAME_ITEM_ROTATE: Int = 1044
        const val EVENT_SOUND_CAMERA: Int = 1050
        const val EVENT_SOUND_EXPERIENCE_ORB_PICKUP: Int = 1051
        const val EVENT_SOUND_TOTEM_USED: Int = 1052
        const val EVENT_SOUND_ARMOR_STAND_BREAK: Int = 1060
        const val EVENT_SOUND_ARMOR_STAND_HIT: Int = 1061
        const val EVENT_SOUND_ARMOR_STAND_LAND: Int = 1062
        const val EVENT_SOUND_ARMOR_STAND_PLACE: Int = 1063
        const val EVENT_SOUND_POINTED_DRIPSTONE_LAND: Int = 1064
        const val EVENT_SOUND_DYE_USED: Int = 1065
        const val EVENT_SOUND_INK_SACE_USED: Int = 1066
        const val EVENT_SOUND_AMETHYST_RESONATE: Int = 1067
        const val EVENT_PARTICLE_SHOOT: Int = 2000
        const val EVENT_PARTICLE_DESTROY_BLOCK: Int = 2001
        const val EVENT_PARTICLE_POTION_SPLASH: Int = 2002
        const val EVENT_PARTICLE_EYE_OF_ENDER_DEATH: Int = 2003
        const val EVENT_PARTICLE_MOB_BLOCK_SPAWN: Int = 2004
        const val EVENT_PARTICLE_CROP_GROWTH: Int = 2005
        const val EVENT_PARTICLE_SOUND_GUARDIAN_GHOST: Int = 2006
        const val EVENT_PARTICLE_DEATH_SMOKE: Int = 2007
        const val EVENT_PARTICLE_DENY_BLOCK: Int = 2008
        const val EVENT_PARTICLE_GENERIC_SPAWN: Int = 2009
        const val EVENT_PARTICLE_DRAGON_EGG: Int = 2010
        const val EVENT_PARTICLE_CROP_EATEN: Int = 2011
        const val EVENT_PARTICLE_CRIT: Int = 2012
        const val EVENT_PARTICLE_TELEPORT: Int = 2013
        const val EVENT_PARTICLE_CRACK_BLOCK: Int = 2014
        const val EVENT_PARTICLE_BUBBLES: Int = 2015
        const val EVENT_PARTICLE_EVAPORATE: Int = 2016
        const val EVENT_PARTICLE_DESTROY_ARMOR_STAND: Int = 2017
        const val EVENT_PARTICLE_BREAKING_EGG: Int = 2018
        const val EVENT_PARTICLE_DESTROY_EGG: Int = 2019
        const val EVENT_PARTICLE_EVAPORATE_WATER: Int = 2020
        const val EVENT_PARTICLE_DESTROY_BLOCK_NO_SOUND: Int = 2021
        const val EVENT_PARTICLE_KNOCKBACK_ROAR: Int = 2022
        const val EVENT_PARTICLE_TELEPORT_TRAIL: Int = 2023
        const val EVENT_PARTICLE_POINT_CLOUD: Int = 2024
        const val EVENT_PARTICLE_EXPLOSION: Int = 2025
        const val EVENT_PARTICLE_BLOCK_EXPLOSION: Int = 2026
        const val EVENT_PARTICLE_VIBRATION_SIGNAL: Int = 2027
        const val EVENT_PARTICLE_DRIPSTONE_DRIP: Int = 2028
        const val EVENT_PARTICLE_FIZZ_EFFECT: Int = 2029
        const val EVENT_PARTICLE_WAX_ON: Int = 2030
        const val EVENT_PARTICLE_WAX_OFF: Int = 2031
        const val EVENT_PARTICLE_SCRAPE: Int = 2032
        const val EVENT_PARTICLE_ELECTRIC_SPARK: Int = 2033
        const val EVENT_PARTICLE_TURTLE_EGG: Int = 2034
        const val EVENT_PARTICLE_SCULK_SHRIEK: Int = 2035
        const val EVENT_SCULK_CATALYST_BLOOM: Int = 2036
        const val EVENT_SCULK_CHARGE: Int = 2037
        const val EVENT_SCULK_CHARGE_POP: Int = 2038
        const val EVENT_SONIC_EXPLOSION: Int = 2039
        const val EVENT_DUST_PLUME: Int = 2040
        const val EVENT_START_RAINING: Int = 3001
        const val EVENT_START_THUNDERSTORM: Int = 3002
        const val EVENT_STOP_RAINING: Int = 3003
        const val EVENT_STOP_THUNDERSTORM: Int = 3004
        const val EVENT_GLOBAL_PAUSE: Int = 3005
        const val EVENT_SIM_TIME_STEP: Int = 3006
        const val EVENT_SIM_TIME_SCALE: Int = 3007
        const val EVENT_ACTIVATE_BLOCK: Int = 3500
        const val EVENT_CAULDRON_EXPLODE: Int = 3501
        const val EVENT_CAULDRON_DYE_ARMOR: Int = 3502
        const val EVENT_CAULDRON_CLEAN_ARMOR: Int = 3503
        const val EVENT_CAULDRON_FILL_POTION: Int = 3504
        const val EVENT_CAULDRON_TAKE_POTION: Int = 3505
        const val EVENT_CAULDRON_FILL_WATER: Int = 3506
        const val EVENT_CAULDRON_TAKE_WATER: Int = 3507
        const val EVENT_CAULDRON_ADD_DYE: Int = 3508
        const val EVENT_CAULDRON_CLEAN_BANNER: Int = 3509
        const val EVENT_CAULDRON_FLUSH: Int = 3510
        const val EVENT_AGENT_SPAWN_EFFECT: Int = 3511
        const val EVENT_CAULDRON_FILL_LAVA: Int = 3512
        const val EVENT_CAULDRON_TAKE_LAVA: Int = 3513
        const val EVENT_CAULDRON_FILL_POWDER_SNOW: Int = 3514
        const val EVENT_CAULDRON_TAKE_POWDER_SNOW: Int = 3515
        const val EVENT_BLOCK_START_BREAK: Int = 3600
        const val EVENT_BLOCK_STOP_BREAK: Int = 3601
        const val EVENT_BLOCK_UPDATE_BREAK: Int = 3602
        const val EVENT_PARTICLE_BREAK_BLOCK_DOWN: Int = 3603
        const val EVENT_PARTICLE_BREAK_BLOCK_UP: Int = 3604
        const val EVENT_PARTICLE_BREAK_BLOCK_NORTH: Int = 3605
        const val EVENT_PARTICLE_BREAK_BLOCK_SOUTH: Int = 3606
        const val EVENT_PARTICLE_BREAK_BLOCK_WEST: Int = 3607
        const val EVENT_PARTICLE_BREAK_BLOCK_EAST: Int = 3608
        const val EVENT_PARTICLE_SHOOT_WHITE_SMOKE: Int = 3609
        const val EVENT_PARTICLE_BREEZE_WIND_EXPLOSION: Int = 3610
        const val EVENT_PARTICLE_TRAIL_SPAWNER_DETECTION: Int = 3611
        const val EVENT_PARTICLE_TRAIL_SPAWNER_SPAWNING: Int = 3612
        const val EVENT_PARTICLE_TRAIL_SPAWNER_EJECTING: Int = 3613
        const val EVENT_PARTICLE_WIND_EXPLOSION: Int = 3614
        const val EVENT_PARTICLE_TRIAL_SPAWNER_DETECTION_CHARGED: Int = 3615
        const val EVENT_PARTICLE_TRIAL_SPAWNER_BECOME_CHARGED: Int = 3616
        const val EVENT_SLEEPING_PLAYERS: Int = 9801
        const val EVENT_JUMP_PREVENTED: Int = 9810
        const val EVENT_ANIMATION_VAULT_ACTIVATE: Int = 9811
        const val EVENT_ANIMATION_VAULT_DEACTIVATE: Int = 9812
        const val EVENT_ANIMATION_VAULT_EJECT_ITEM: Int = 9813
        const val EVENT_ANIMATION_SPAWN_COBWEB: Int = 9814
        const val EVENT_PARTICLE_SMASH_ATTACK_GROUND_DUST: Int = 9815
        const val EVENT_PARTICLE_CREAKING_HEART_TRAIL: Int = 9816
        const val EVENT_ADD_PARTICLE_MASK: Int = 0x4000

        override val id: Int
            get() = ProtocolInfo.LEVEL_EVENT_PACKET

        override fun serialize(value: LevelEventPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.eventType, stream)
            Vector3f.serialize(value.position, stream)
            ProtoVAR.Int.serialize(value.eventData, stream)
        }

        override fun deserialize(stream: Source): LevelEventPacket {
            return LevelEventPacket(
                eventType = ProtoVAR.Int.deserialize(stream),
                position = Vector3f.deserialize(stream),
                eventData = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
