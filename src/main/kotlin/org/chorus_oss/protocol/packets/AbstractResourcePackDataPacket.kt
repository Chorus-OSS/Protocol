package org.chorus_oss.protocol.packets

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.utils.Version
import java.util.*

abstract class AbstractResourcePackDataPacket : DataPacket() {
    abstract var packVersion: Version?

    abstract var packId: UUID?

    protected fun decodePackInfo(byteBuf: ByteBuf) {
        val packInfo = Proto.String.deserialize(byteBuf)
        val packInfoParts = packInfo.split("_", ignoreCase = false, limit = 2)
        packId = try {
            UUID.fromString(packInfoParts[0])
        } catch (exception: IllegalArgumentException) {
            null
        }
        packVersion = if (packInfoParts.size > 1) Version(packInfoParts[1]) else null
    }

    protected fun encodePackInfo(byteBuf: ByteBuf) {
        val packId = packId
        val packVersion = packVersion
        var packInfo = packId?.toString() ?: UUID(0, 0).toString()
        if (packVersion != null) {
            packInfo += "_$packVersion"
        }
        Proto.String.serialize(packInfo, byteBuf)
    }
}
