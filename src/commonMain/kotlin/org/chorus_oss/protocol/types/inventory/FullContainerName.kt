package org.chorus_oss.protocol.types.inventory

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.itemstack.ContainerSlotType

data class FullContainerName(
    val container: ContainerSlotType,
    val dynamicId: Int? = null
) {
    companion object : ProtoCodec<FullContainerName> {
        override fun serialize(
            value: FullContainerName,
            stream: Sink
        ) {
            ContainerSlotType.serialize(value.container, stream)
            ProtoHelper.serializeNullable(value.dynamicId, stream, ProtoLE.Int)
        }

        override fun deserialize(stream: Source): FullContainerName {
            return FullContainerName(
                container = ContainerSlotType.deserialize(stream),
                dynamicId = ProtoHelper.deserializeNullable(stream, ProtoLE.Int)
            )
        }
    }
}
