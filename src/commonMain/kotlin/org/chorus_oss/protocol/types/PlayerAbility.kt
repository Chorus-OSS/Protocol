package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class PlayerAbility(
    // Feature bits for RequestPermissionsPacket
    val bit: UShort = 0u
) {
    Build(0x1u),
    Mine(0x2u),
    DoorsAndSwitches(0x4u),
    OpenContainers(0x8u),
    AttackPlayers(0x10u),
    AttackMobs(0x20u),
    OperatorCommands(0x40u),
    Teleport(0x80u),

    Invulnerable,
    Flying,
    MayFly,
    Instabuild,
    Lightning,
    FlySpeed,
    WalkSpeed,
    Muted,
    WorldBuilder,
    NoClip,
    PrivilegedBuilder,
    VerticalFlySpeed;

    companion object : ProtoCodec<PlayerAbility> {
        override fun serialize(value: PlayerAbility, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): PlayerAbility {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}
