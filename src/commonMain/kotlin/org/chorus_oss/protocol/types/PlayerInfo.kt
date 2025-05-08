package org.chorus_oss.protocol.types

import org.chorus_oss.chorus.entity.data.Skin
import org.chorus_oss.chorus.utils.ClientChainData


import java.util.*

open class PlayerInfo(
    val username: String,
    val uniqueId: UUID,
    val skin: Skin,
    val data: ClientChainData,
)
