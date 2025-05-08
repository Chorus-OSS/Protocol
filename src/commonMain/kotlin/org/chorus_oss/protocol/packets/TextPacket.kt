package org.chorus_oss.protocol.packets

import io.netty.util.internal.EmptyArrays

import java.util.function.Function

class TextPacket : DataPacket() {
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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<TextPacket> {
        override fun decode(byteBuf: ByteBuf): TextPacket {
            val packet = TextPacket()

            packet.type = byteBuf.readByte()
            packet.isLocalized = byteBuf.readBoolean() || packet.type == TYPE_TRANSLATION
            when (packet.type) {
                TYPE_CHAT, TYPE_WHISPER, TYPE_ANNOUNCEMENT -> {
                    packet.source = byteBuf.readString()
                    packet.message = byteBuf.readString()
                }

                TYPE_RAW, TYPE_TIP, TYPE_SYSTEM, TYPE_OBJECT, TYPE_OBJECT_WHISPER -> packet.message =
                    byteBuf.readString()

                TYPE_TRANSLATION, TYPE_POPUP, TYPE_JUKEBOX_POPUP -> {
                    packet.message = byteBuf.readString()
                    packet.parameters = byteBuf.readArray<String>(
                        String::class.java,
                        Function { obj: ByteBuf -> obj.readString() })
                }
            }
            packet.xboxUserId = byteBuf.readString()
            packet.platformChatId = byteBuf.readString()
            packet.filteredMessage = byteBuf.readString()

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
