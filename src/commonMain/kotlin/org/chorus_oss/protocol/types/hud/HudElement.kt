package org.chorus_oss.protocol.types.hud

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class HudElement {
    PaperDoll,
    Armor,
    ToolTips,
    TouchControls,
    Crosshair,
    Hotbar,
    Health,
    ProgressBar,
    Hunger,
    AirBubblesBar,
    VehicleHealth,
    StatusEffects,
    ItemText;

    companion object : ProtoCodec<HudElement> {
        override fun serialize(value: HudElement, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): HudElement {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}
