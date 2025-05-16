package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Short

data class DefaultItemDescriptor(
    val networkID: Short,
    val metadataValue: Short?,
) : ItemDescriptor() {
    override val type: ItemDescriptor.Companion.Type
        get() = ItemDescriptor.Companion.Type.Default

    companion object : ProtoCodec<DefaultItemDescriptor> {
        override fun serialize(
            value: DefaultItemDescriptor,
            stream: Sink
        ) {
            ProtoLE.Short.serialize(value.networkID, stream)
            when (value.networkID != 0.toShort()) {
                true -> (value.metadataValue as Short).let { ProtoLE.Short.serialize(it, stream) }
                false -> Unit
            }
        }

        override fun deserialize(stream: Source): DefaultItemDescriptor {
            val networkID: Short
            return DefaultItemDescriptor(
                networkID = ProtoLE.Short.deserialize(stream).also { networkID = it },
                metadataValue = when (networkID != 0.toShort()) {
                    true -> ProtoLE.Short.deserialize(stream)
                    false -> null
                }
            )
        }

    }
}
