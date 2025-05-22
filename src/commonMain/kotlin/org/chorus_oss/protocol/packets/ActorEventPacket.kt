package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorRuntimeID


class ActorEventPacket(
    val actorRuntimeID: ActorRuntimeID,
    val eventType: Type,
    val eventData: Int,
) : Packet(id) {
    companion object : PacketCodec<ActorEventPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class Type(val id: Byte) {
            NONE(0),
            JUMP(1),
            HURT_ANIMATION(2),
            DEATH_ANIMATION(3),
            ARM_SWING(4),
            ATTACK_STOP(5),
            TAME_FAIL(6),
            TAME_SUCCESS(7),
            SHAKE_WET(8),
            USE_ITEM(9),
            EAT_GRASS_ANIMATION(10),
            FISH_HOOK_BUBBLE(11),
            FISH_HOOK_POSITION(12),
            FISH_HOOK_HOOK(13),
            FISH_HOOK_TEASE(14),
            SQUID_INK_CLOUD(15),
            ZOMBIE_VILLAGER_CURE(16),
            AMBIENT_SOUND(17),
            RESPAWN(18),
            IRON_GOLEM_OFFER_FLOWER(19),
            IRON_GOLEM_WITHDRAW_FLOWER(20),
            LOVE_PARTICLES(21),
            VILLAGER_ANGRY(22),
            VILLAGER_HAPPY(23),
            WITCH_SPELL_PARTICLES(24),
            FIREWORK_EXPLOSION(25),
            IN_LOVE_HEARTS(26),
            SILVERFISH_SPAWN_ANIMATION(27),
            GUARDIAN_ATTACK_ANIMATION(28),
            WITCH_DRINK_POTION(29),
            WITCH_THROW_POTION(30),
            MINECART_TNT_PRIME_FUSE(31),
            PRIME_CREEPER(32),
            AIR_SUPPLY(33),
            ENCHANT(34),
            ELDER_GUARDIAN_CURSE(35),
            AGENT_ARM_SWING(36),
            ENDER_DRAGON_DEATH(37),
            DUST_PARTICLES(38),
            ARROW_SHAKE(39),
            EATING_ITEM(57),
            BABY_ANIMAL_FEED(60),
            DEATH_SMOKE_CLOUD(61),
            COMPLETE_TRADE(62),
            REMOVE_LEASH(63),
            CARAVAN(64),
            CONSUME_TOTEM(65),
            PLAYER_CHECK_TREASURE_HUNTER_ACHIEVEMENT(66),
            ENTITY_SPAWN(67),
            DRAGON_PUKE(68),
            MERGE_ITEMS(69),
            START_SWIMMING(70),
            BALLOON_POP(71),
            TREASURE_HUNT(72),
            SUMMON_AGENT(73),
            FINISHED_CHARGING_CROSSBOW(74),
            LANDED_ON_GROUND(75),
            ENTITY_GROW_UP(76),
            VIBRATION_DETECTED(77),
            DRINK_MILK(78);

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.id, stream)
                }

                override fun deserialize(stream: Source): Type {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.id == it }!!
                    }
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.ACTOR_EVENT_PACKET

        override fun serialize(value: ActorEventPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            Type.serialize(value.eventType, stream)
            ProtoVAR.Int.serialize(value.eventData, stream)
        }

        override fun deserialize(stream: Source): ActorEventPacket {
            return ActorEventPacket(
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                eventType = Type.deserialize(stream),
                eventData = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
