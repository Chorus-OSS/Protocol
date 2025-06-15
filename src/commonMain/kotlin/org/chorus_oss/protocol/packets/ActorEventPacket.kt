package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
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
        enum class Type(val id: Byte) {
            None(0),
            Jump(1),
            HurtAnimation(2),
            DeathAnimation(3),
            ArmSwing(4),
            AttackStop(5),
            TameFail(6),
            TameSuccess(7),
            ShakeWet(8),
            UseItem(9),
            EatGrassAnimation(10),
            FishHookBubble(11),
            FishHookPosition(12),
            FishHookHook(13),
            FishHookTease(14),
            SquidInkCloud(15),
            ZombieVillagerCure(16),
            AmbientSound(17),
            Respawn(18),
            IronGolemOfferFlower(19),
            IronGolemWithdrawFlower(20),
            LoveParticles(21),
            VillagerAngry(22),
            VillagerHappy(23),
            WitchSpellParticles(24),
            FireworkExplosion(25),
            InLoveHearts(26),
            SilverfishSpawnAnimation(27),
            GuardianAttackAnimation(28),
            WitchDrinkPotion(29),
            WitchThrowPotion(30),
            MinecartTntPrimeFuse(31),
            PrimeCreeper(32),
            AirSupply(33),
            Enchant(34),
            ElderGuardianCurse(35),
            AgentArmSwing(36),
            EnderDragonDeath(37),
            DustParticles(38),
            ArrowShake(39),
            EatingItem(57),
            BabyAnimalFeed(60),
            DeathSmokeCloud(61),
            CompleteTrade(62),
            RemoveLeash(63),
            Caravan(64),
            ConsumeTotem(65),
            PlayerCheckTreasureHunterAchievement(66),
            EntitySpawn(67),
            DragonPuke(68),
            MergeItems(69),
            StartSwimming(70),
            BalloonPop(71),
            TreasureHunt(72),
            SummonAgent(73),
            FinishedChargingCrossbow(74),
            LandedOnGround(75),
            EntityGrowUp(76),
            VibrationDetected(77),
            DrinkMilk(78);

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

        override val id: Int = 27

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
