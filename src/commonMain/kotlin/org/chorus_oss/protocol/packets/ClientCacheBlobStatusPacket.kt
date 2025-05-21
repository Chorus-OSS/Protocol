package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.ULong

data class ClientCacheBlobStatusPacket(
    val missHashes: List<ULong>,
    val hitHashes: List<ULong>,
) : Packet(id) {
    companion object : PacketCodec<ClientCacheBlobStatusPacket> {
        override val id: Int
            get() = ProtocolInfo.CLIENT_CACHE_BLOB_STATUS_PACKET

        override fun serialize(
            value: ClientCacheBlobStatusPacket,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.missHashes.size.toUInt(), stream)
            ProtoVAR.UInt.serialize(value.hitHashes.size.toUInt(), stream)
            value.missHashes.forEach {
                ProtoLE.ULong.serialize(it, stream)
            }
            value.hitHashes.forEach {
                ProtoLE.ULong.serialize(it, stream)
            }
        }

        override fun deserialize(stream: Source): ClientCacheBlobStatusPacket {
            val missHashesSize = ProtoVAR.UInt.deserialize(stream)
            val hitHashesSize = ProtoVAR.UInt.deserialize(stream)
            return ClientCacheBlobStatusPacket(
                missHashes = List(missHashesSize.toInt()) {
                    ProtoLE.ULong.deserialize(stream)
                },
                hitHashes = List(hitHashesSize.toInt()) {
                    ProtoLE.ULong.deserialize(stream)
                }
            )
        }
    }
}
