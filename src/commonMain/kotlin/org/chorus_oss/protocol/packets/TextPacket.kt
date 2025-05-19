package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String

data class TextPacket(
    val textType: TextType,
    val needsTranslation: Boolean,
    val sourceName: String?,
    val message: String,
    val parameters: List<String>?,
    val xuid: String,
    val platformChatID: String,
    val filteredMessage: String,
) : Packet(id) {
    companion object : PacketCodec<TextPacket> {
        enum class TextType {
            Raw,
            Chat,
            Translation,
            Popup,
            JukeboxPopup,
            Tip,
            System,
            Whisper,
            Announcement,
            ObjectWhisper,
            Object,
            ObjectAnnouncement;

            companion object : ProtoCodec<TextType> {
                override fun serialize(
                    value: TextType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): TextType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.TEXT_PACKET

        override fun serialize(value: TextPacket, stream: Sink) {
            TextType.serialize(value.textType, stream)
            Proto.Boolean.serialize(value.needsTranslation, stream)
            when (value.textType) {
                TextType.Chat,
                TextType.Whisper,
                TextType.Announcement -> Proto.String.serialize(value.sourceName as String, stream)
                else -> Unit
            }
            Proto.String.serialize(value.message, stream)
            when (value.textType) {
                TextType.Translation,
                TextType.Popup,
                TextType.JukeboxPopup -> ProtoHelper.serializeList(value.parameters as List<String>, stream, Proto.String)
                else -> Unit
            }
            Proto.String.serialize(value.xuid, stream)
            Proto.String.serialize(value.platformChatID, stream)
            Proto.String.serialize(value.filteredMessage, stream)
        }

        override fun deserialize(stream: Source): TextPacket {
            val textType: TextType
            return TextPacket(
                textType = TextType.deserialize(stream).also { textType = it },
                needsTranslation = Proto.Boolean.deserialize(stream),
                sourceName = when (textType) {
                    TextType.Chat,
                    TextType.Whisper,
                    TextType.Announcement -> Proto.String.deserialize(stream)
                    else -> null
                },
                message = Proto.String.deserialize(stream),
                parameters = when (textType) {
                    TextType.Translation,
                    TextType.Popup,
                    TextType.JukeboxPopup -> ProtoHelper.deserializeList(stream, Proto.String)
                    else -> null
                },
                xuid = Proto.String.deserialize(stream),
                platformChatID = Proto.String.deserialize(stream),
                filteredMessage = Proto.String.deserialize(stream),
            )
        }
    }
}
