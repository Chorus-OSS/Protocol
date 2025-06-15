package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class BeaconPaymentRequestAction(
    val primaryEffect: Int,
    val secondaryEffect: Int,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.BeaconPayment

    companion object : ProtoCodec<BeaconPaymentRequestAction> {
        override fun serialize(
            value: BeaconPaymentRequestAction,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.primaryEffect, stream)
            ProtoVAR.Int.serialize(value.secondaryEffect, stream)
        }

        override fun deserialize(stream: Source): BeaconPaymentRequestAction {
            return BeaconPaymentRequestAction(
                primaryEffect = ProtoVAR.Int.deserialize(stream),
                secondaryEffect = ProtoVAR.Int.deserialize(stream)
            )
        }
    }
}
