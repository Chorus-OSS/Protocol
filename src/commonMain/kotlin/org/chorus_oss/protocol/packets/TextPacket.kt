package org.chorus_oss.protocol.packets

import io.netty.util.internal.EmptyArrays

import java.util.function.Function

class TextPacket : Packet(id) {
    @JvmField
    var type: Byte = 0

    @JvmField
    var source: String = ""

    @JvmField
    var message: String = ""

    @JvmField
    var parameters: Array<String> = EmptyArrays.EMPTY_STRINGS
    var isLocalized: Boolean = false
    var xboxUserId: String = ""
    var platformChatId: String = ""
    var filteredMessage: String = ""

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(type.toInt())
        byteBuf.writeBoolean(this.isLocalized || type == TYPE_TRANSLATION)
        when (this.type) {
            TYPE_CHAT, TYPE_WHISPER, TYPE_ANNOUNCEMENT -> {
                byteBuf.writeString(this.source)
                byteBuf.writeString(this.message)
            }

            TYPE_RAW, TYPE_TIP, TYPE_SYSTEM, TYPE_OBJECT, TYPE_OBJECT_WHISPER -> byteBuf.writeString(
                this.message
            )

            TYPE_TRANSLATION, TYPE_POPUP, TYPE_JUKEBOX_POPUP -> {
                byteBuf.writeString(this.message)
                byteBuf.writeUnsignedVarInt(parameters.size)
                for (parameter in this.parameters) {
                    byteBuf.writeString(parameter)
                }
            }
        }
        byteBuf.writeString(this.xboxUserId)
        byteBuf.writeString(this.platformChatId)
        byteBuf.writeString(this.filteredMessage)
    }

    override fun pid(): Int {
        return ProtocolInfo.TEXT_PACKET
    }



    companion object : PacketCodec<TextPacket> {
        override fun deserialize(stream: Source): TextPacket {
            val packet = TextPacket()

            packet.type = Proto.Byte.deserialize(stream)
            packet.isLocalized = Proto.Boolean.deserialize(stream) || packet.type == TYPE_TRANSLATION
            when (packet.type) {
                TYPE_CHAT, TYPE_WHISPER, TYPE_ANNOUNCEMENT -> {
                    packet.source = Proto.String.deserialize(stream)
                    packet.message = Proto.String.deserialize(stream)
                }

                TYPE_RAW, TYPE_TIP, TYPE_SYSTEM, TYPE_OBJECT, TYPE_OBJECT_WHISPER -> packet.message =
                    Proto.String.deserialize(stream)

                TYPE_TRANSLATION, TYPE_POPUP, TYPE_JUKEBOX_POPUP -> {
                    packet.message = Proto.String.deserialize(stream)
                    packet.parameters = byteBuf.readArray<String>(
                        String::class.java,
                        Function { obj: ByteBuf -> obj.readString() })
                }
            }
            packet.xboxUserId = Proto.String.deserialize(stream)
            packet.platformChatId = Proto.String.deserialize(stream)
            packet.filteredMessage = Proto.String.deserialize(stream)

            return packet
        }

        const val TYPE_RAW: Byte = 0
        const val TYPE_CHAT: Byte = 1
        const val TYPE_TRANSLATION: Byte = 2
        const val TYPE_POPUP: Byte = 3
        const val TYPE_JUKEBOX_POPUP: Byte = 4
        const val TYPE_TIP: Byte = 5
        const val TYPE_SYSTEM: Byte = 6
        const val TYPE_WHISPER: Byte = 7
        const val TYPE_ANNOUNCEMENT: Byte = 8
        const val TYPE_OBJECT: Byte = 9
        const val TYPE_OBJECT_WHISPER: Byte = 10
    }
}
