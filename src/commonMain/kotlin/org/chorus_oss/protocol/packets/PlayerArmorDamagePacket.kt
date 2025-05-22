package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import kotlin.experimental.and


data class PlayerArmorDamagePacket(
    val bitset: Byte,
    val helmetDamage: Int,
    val chestplateDamage: Int,
    val leggingsDamage: Int,
    val bootsDamage: Int,
    val bodyDamage: Int,
) : Packet(id) {
    companion object : PacketCodec<PlayerArmorDamagePacket> {
        init {
            PacketRegistry.register(this)
        }

        const val FLAG_HELMET: Byte = 0x1
        const val FLAG_CHESTPLATE: Byte = 0x2
        const val FLAG_LEGGINGS: Byte = 0x4
        const val FLAG_BOOTS: Byte = 0x8
        const val FLAG_BODY: Byte = 0x10

        override val id: Int
            get() = ProtocolInfo.PLAYER_ARMOR_DAMAGE_PACKET

        override fun serialize(
            value: PlayerArmorDamagePacket,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.bitset, stream)
            when (value.bitset and FLAG_HELMET != 0.toByte()) {
                true -> ProtoVAR.Int.serialize(value.helmetDamage, stream)
                false -> Unit
            }
            when (value.bitset and FLAG_CHESTPLATE != 0.toByte()) {
                true -> ProtoVAR.Int.serialize(value.chestplateDamage, stream)
                false -> Unit
            }
            when (value.bitset and FLAG_LEGGINGS != 0.toByte()) {
                true -> ProtoVAR.Int.serialize(value.leggingsDamage, stream)
                false -> Unit
            }
            when (value.bitset and FLAG_BOOTS != 0.toByte()) {
                true -> ProtoVAR.Int.serialize(value.bootsDamage, stream)
                false -> Unit
            }
            when (value.bitset and FLAG_BODY != 0.toByte()) {
                true -> ProtoVAR.Int.serialize(value.bodyDamage, stream)
                false -> Unit
            }
        }

        override fun deserialize(stream: Source): PlayerArmorDamagePacket {
            val bitset: Byte
            return PlayerArmorDamagePacket(
                bitset = Proto.Byte.deserialize(stream).also { bitset = it },
                helmetDamage = when (bitset and FLAG_HELMET != 0.toByte()) {
                    true -> ProtoVAR.Int.deserialize(stream)
                    false -> 0
                },
                chestplateDamage = when (bitset and FLAG_CHESTPLATE != 0.toByte()) {
                    true -> ProtoVAR.Int.deserialize(stream)
                    false -> 0
                },
                leggingsDamage = when (bitset and FLAG_LEGGINGS != 0.toByte()) {
                    true -> ProtoVAR.Int.deserialize(stream)
                    false -> 0
                },
                bootsDamage = when (bitset and FLAG_BOOTS != 0.toByte()) {
                    true -> ProtoVAR.Int.deserialize(stream)
                    false -> 0
                },
                bodyDamage = when (bitset and FLAG_BODY != 0.toByte()) {
                    true -> ProtoVAR.Int.deserialize(stream)
                    false -> 0
                },
            )
        }
    }
}
