package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3


data class SetSpawnPositionPacket(
    val spawnType: SpawnType,
    val position: IVector3,
    val dimension: Int,
    val spawnPosition: IVector3,
) : Packet(id) {
    companion object : PacketCodec<SetSpawnPositionPacket> {
        init { PacketRegistry.register(this) }

        enum class SpawnType {
            Player,
            World;

            companion object : ProtoCodec<SpawnType> {
                override fun serialize(
                    value: SpawnType,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): SpawnType {
                    return entries[ProtoVAR.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.SET_SPAWN_POSITION_PACKET

        override fun serialize(value: SetSpawnPositionPacket, stream: Sink) {
            SpawnType.serialize(value.spawnType, stream)
            UIVector3.serialize(value.position, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            UIVector3.serialize(value.spawnPosition, stream)
        }

        override fun deserialize(stream: Source): SetSpawnPositionPacket {
            return SetSpawnPositionPacket(
                spawnType = SpawnType.deserialize(stream),
                position = UIVector3.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
                spawnPosition = UIVector3.deserialize(stream),
            )
        }
    }
}
