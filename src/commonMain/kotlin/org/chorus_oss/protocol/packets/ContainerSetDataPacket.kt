package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int


class ContainerSetDataPacket(
    val containerID: Byte,
    val property: Int,
    val value: Int,
) : Packet(id) {
    companion object : PacketCodec<ContainerSetDataPacket> {
        const val FURNACE_TICK_COUNT: Int = 0
        const val FURNACE_LIT_TIME: Int = 1
        const val FURNACE_LIT_DURATION: Int = 2
        const val FURNACE_STORED_XP: Int = 3
        const val FURNACE_FUEL_AUX: Int = 4

        const val BREWING_STAND_BREW_TIME: Int = 0
        const val BREWING_STAND_FUEL_AMOUNT: Int = 1
        const val BREWING_STAND_FUEL_TOTAL: Int = 2

        override val id: Int
            get() = ProtocolInfo.CONTAINER_SET_DATA_PACKET

        override fun serialize(value: ContainerSetDataPacket, stream: Sink) {
            Proto.Byte.serialize(value.containerID, stream)
            ProtoVAR.Int.serialize(value.property, stream)
            ProtoVAR.Int.serialize(value.value, stream)
        }

        override fun deserialize(stream: Source): ContainerSetDataPacket {
            return ContainerSetDataPacket(
                containerID = Proto.Byte.deserialize(stream),
                property = ProtoVAR.Int.deserialize(stream),
                value = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
