package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readString
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.EducationExternalLinkSettings

data class EducationSettingsPacket(
    val codeBuilderDefaultURI: String,
    val codeBuilderTitle: String,
    val canResizeCodeBuilder: Boolean,
    val disableLegacyTitleBar: Boolean,
    val postProcessFilter: String,
    val screenshotBorderPath: String,
    val canModifyBlocks: Boolean? = null,
    val overrideURI: String? = null,
    val hasQuiz: Boolean,
    val externalLinkSettings: EducationExternalLinkSettings? = null,
) : Packet(id) {
    companion object : PacketCodec<EducationSettingsPacket> {
        override val id: Int
            get() = ProtocolInfo.EDUCATION_SETTINGS_PACKET

        override fun serialize(
            value: EducationSettingsPacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.codeBuilderDefaultURI, stream)
            Proto.String.serialize(value.codeBuilderTitle, stream)
            Proto.Boolean.serialize(value.canResizeCodeBuilder, stream)
            Proto.Boolean.serialize(value.disableLegacyTitleBar, stream)
            Proto.String.serialize(value.postProcessFilter, stream)
            Proto.String.serialize(value.screenshotBorderPath, stream)
            ProtoHelper.serializeNullable(value.canModifyBlocks, stream, Proto.Boolean::serialize)
            ProtoHelper.serializeNullable(value.overrideURI, stream, Proto.String::serialize)
            Proto.Boolean.serialize(value.hasQuiz, stream)
            ProtoHelper.serializeNullable(value.externalLinkSettings, stream, EducationExternalLinkSettings::serialize)
        }

        override fun deserialize(stream: Source): EducationSettingsPacket {
            return EducationSettingsPacket(
                codeBuilderDefaultURI = Proto.String.deserialize(stream),
                codeBuilderTitle = Proto.String.deserialize(stream),
                canResizeCodeBuilder = Proto.Boolean.deserialize(stream),
                disableLegacyTitleBar = Proto.Boolean.deserialize(stream),
                postProcessFilter = Proto.String.deserialize(stream),
                screenshotBorderPath = Proto.String.deserialize(stream),
                canModifyBlocks = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize),
                overrideURI = ProtoHelper.deserializeNullable(stream, Proto.String::deserialize),
                hasQuiz = Proto.Boolean.deserialize(stream),
                externalLinkSettings = ProtoHelper.deserializeNullable(stream, EducationExternalLinkSettings::deserialize),
            )
        }
    }
}
