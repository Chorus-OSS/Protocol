package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos


data class SetSpawnPositionPacket(
    val spawnType: SpawnType,
    val position: BlockPos,
    val dimension: Int,
    val spawnPosition: BlockPos,
) : Packet(id) {
    companion object : PacketCodec<SetSpawnPositionPacket> {
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

        override val id: Int = 43

        override fun serialize(value: SetSpawnPositionPacket, stream: Sink) {
            SpawnType.serialize(value.spawnType, stream)
            NetBlockPos.serialize(value.position, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            NetBlockPos.serialize(value.spawnPosition, stream)
        }

        override fun deserialize(stream: Source): SetSpawnPositionPacket {
            return SetSpawnPositionPacket(
                spawnType = SpawnType.deserialize(stream),
                position = NetBlockPos.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
                spawnPosition = NetBlockPos.deserialize(stream),
            )
        }
    }
}
