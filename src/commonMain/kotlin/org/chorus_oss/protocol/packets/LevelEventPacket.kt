package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.Vector3f

@Suppress("unused")
data class LevelEventPacket(
    val eventType: Int,
    val position: Vector3f,
    val eventData: Int,
) : Packet(id) {
    companion object : PacketCodec<LevelEventPacket> {
        const val UNDEFINED: Int = 0
        const val SOUND_CLICK: Int = 1000
        const val SOUND_CLICK_FAIL: Int = 1001
        const val SOUND_LAUNCH: Int = 1002
        const val SOUND_DOOR_OPEN: Int = 1003
        const val SOUND_FIZZ: Int = 1004
        const val SOUND_FUSE: Int = 1005
        const val SOUND_PLAY_RECORDING: Int = 1006
        const val SOUND_GHAST_WARNING: Int = 1007
        const val SOUND_GHAST_FIREBALL: Int = 1008
        const val SOUND_BLAZE_FIREBALL: Int = 1009
        const val SOUND_ZOMBIE_DOOR_BUMP: Int = 1010
        const val SOUND_ZOMBIE_DOOR_CRASH: Int = 1012
        const val SOUND_ZOMBIE_INFECTED: Int = 1016
        const val SOUND_ZOMBIE_CONVERTED: Int = 1017
        const val SOUND_ENDERMAN_TELEPORT: Int = 1018
        const val SOUND_ANVIL_BROKEN: Int = 1020
        const val SOUND_ANVIL_USED: Int = 1021
        const val SOUND_ANVIL_LAND: Int = 1022
        const val SOUND_INFINITY_ARROW_PICKUP: Int = 1030
        const val SOUND_TELEPORT_ENDERPEARL: Int = 1032
        const val SOUND_ITEMFRAME_ITEM_ADD: Int = 1040
        const val SOUND_ITEMFRAME_BREAK: Int = 1041
        const val SOUND_ITEMFRAME_PLACE: Int = 1042
        const val SOUND_ITEMFRAME_ITEM_REMOVE: Int = 1043
        const val SOUND_ITEMFRAME_ITEM_ROTATE: Int = 1044
        const val SOUND_CAMERA: Int = 1050
        const val SOUND_EXPERIENCE_ORB_PICKUP: Int = 1051
        const val SOUND_TOTEM_USED: Int = 1052
        const val SOUND_ARMOR_STAND_BREAK: Int = 1060
        const val SOUND_ARMOR_STAND_HIT: Int = 1061
        const val SOUND_ARMOR_STAND_LAND: Int = 1062
        const val SOUND_ARMOR_STAND_PLACE: Int = 1063
        const val SOUND_POINTED_DRIPSTONE_LAND: Int = 1064
        const val SOUND_DYE_USED: Int = 1065
        const val SOUND_INK_SACE_USED: Int = 1066
        const val SOUND_AMETHYST_RESONATE: Int = 1067
        const val PARTICLE_SHOOT: Int = 2000
        const val PARTICLE_DESTROY_BLOCK: Int = 2001
        const val PARTICLE_POTION_SPLASH: Int = 2002
        const val PARTICLE_EYE_OF_ENDER_DEATH: Int = 2003
        const val PARTICLE_MOB_BLOCK_SPAWN: Int = 2004
        const val PARTICLE_CROP_GROWTH: Int = 2005
        const val PARTICLE_SOUND_GUARDIAN_GHOST: Int = 2006
        const val PARTICLE_DEATH_SMOKE: Int = 2007
        const val PARTICLE_DENY_BLOCK: Int = 2008
        const val PARTICLE_GENERIC_SPAWN: Int = 2009
        const val PARTICLE_DRAGON_EGG: Int = 2010
        const val PARTICLE_CROP_EATEN: Int = 2011
        const val PARTICLE_CRIT: Int = 2012
        const val PARTICLE_TELEPORT: Int = 2013
        const val PARTICLE_CRACK_BLOCK: Int = 2014
        const val PARTICLE_BUBBLES: Int = 2015
        const val PARTICLE_EVAPORATE: Int = 2016
        const val PARTICLE_DESTROY_ARMOR_STAND: Int = 2017
        const val PARTICLE_BREAKING_EGG: Int = 2018
        const val PARTICLE_DESTROY_EGG: Int = 2019
        const val PARTICLE_EVAPORATE_WATER: Int = 2020
        const val PARTICLE_DESTROY_BLOCK_NO_SOUND: Int = 2021
        const val PARTICLE_KNOCKBACK_ROAR: Int = 2022
        const val PARTICLE_TELEPORT_TRAIL: Int = 2023
        const val PARTICLE_POINT_CLOUD: Int = 2024
        const val PARTICLE_EXPLOSION: Int = 2025
        const val PARTICLE_BLOCK_EXPLOSION: Int = 2026
        const val PARTICLE_VIBRATION_SIGNAL: Int = 2027
        const val PARTICLE_DRIPSTONE_DRIP: Int = 2028
        const val PARTICLE_FIZZ_EFFECT: Int = 2029
        const val PARTICLE_WAX_ON: Int = 2030
        const val PARTICLE_WAX_OFF: Int = 2031
        const val PARTICLE_SCRAPE: Int = 2032
        const val PARTICLE_ELECTRIC_SPARK: Int = 2033
        const val PARTICLE_TURTLE_EGG: Int = 2034
        const val PARTICLE_SCULK_SHRIEK: Int = 2035
        const val SCULK_CATALYST_BLOOM: Int = 2036
        const val SCULK_CHARGE: Int = 2037
        const val SCULK_CHARGE_POP: Int = 2038
        const val SONIC_EXPLOSION: Int = 2039
        const val DUST_PLUME: Int = 2040
        const val START_RAINING: Int = 3001
        const val START_THUNDERSTORM: Int = 3002
        const val STOP_RAINING: Int = 3003
        const val STOP_THUNDERSTORM: Int = 3004
        const val GLOBAL_PAUSE: Int = 3005
        const val SIM_TIME_STEP: Int = 3006
        const val SIM_TIME_SCALE: Int = 3007
        const val ACTIVATE_BLOCK: Int = 3500
        const val CAULDRON_EXPLODE: Int = 3501
        const val CAULDRON_DYE_ARMOR: Int = 3502
        const val CAULDRON_CLEAN_ARMOR: Int = 3503
        const val CAULDRON_FILL_POTION: Int = 3504
        const val CAULDRON_TAKE_POTION: Int = 3505
        const val CAULDRON_FILL_WATER: Int = 3506
        const val CAULDRON_TAKE_WATER: Int = 3507
        const val CAULDRON_ADD_DYE: Int = 3508
        const val CAULDRON_CLEAN_BANNER: Int = 3509
        const val CAULDRON_FLUSH: Int = 3510
        const val AGENT_SPAWN_EFFECT: Int = 3511
        const val CAULDRON_FILL_LAVA: Int = 3512
        const val CAULDRON_TAKE_LAVA: Int = 3513
        const val CAULDRON_FILL_POWDER_SNOW: Int = 3514
        const val CAULDRON_TAKE_POWDER_SNOW: Int = 3515
        const val BLOCK_START_BREAK: Int = 3600
        const val BLOCK_STOP_BREAK: Int = 3601
        const val BLOCK_UPDATE_BREAK: Int = 3602
        const val PARTICLE_BREAK_BLOCK_DOWN: Int = 3603
        const val PARTICLE_BREAK_BLOCK_UP: Int = 3604
        const val PARTICLE_BREAK_BLOCK_NORTH: Int = 3605
        const val PARTICLE_BREAK_BLOCK_SOUTH: Int = 3606
        const val PARTICLE_BREAK_BLOCK_WEST: Int = 3607
        const val PARTICLE_BREAK_BLOCK_EAST: Int = 3608
        const val PARTICLE_SHOOT_WHITE_SMOKE: Int = 3609
        const val PARTICLE_BREEZE_WIND_EXPLOSION: Int = 3610
        const val PARTICLE_TRAIL_SPAWNER_DETECTION: Int = 3611
        const val PARTICLE_TRAIL_SPAWNER_SPAWNING: Int = 3612
        const val PARTICLE_TRAIL_SPAWNER_EJECTING: Int = 3613
        const val PARTICLE_WIND_EXPLOSION: Int = 3614
        const val PARTICLE_TRIAL_SPAWNER_DETECTION_CHARGED: Int = 3615
        const val PARTICLE_TRIAL_SPAWNER_BECOME_CHARGED: Int = 3616
        const val SLEEPING_PLAYERS: Int = 9801
        const val JUMP_PREVENTED: Int = 9810
        const val ANIMATION_VAULT_ACTIVATE: Int = 9811
        const val ANIMATION_VAULT_DEACTIVATE: Int = 9812
        const val ANIMATION_VAULT_EJECT_ITEM: Int = 9813
        const val ANIMATION_SPAWN_COBWEB: Int = 9814
        const val PARTICLE_SMASH_ATTACK_GROUND_DUST: Int = 9815
        const val PARTICLE_CREAKING_HEART_TRAIL: Int = 9816
        const val ADD_PARTICLE_MASK: Int = 0x4000

        override val id: Int = 25

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
