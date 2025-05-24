package org.chorus_oss.protocol.types.skin

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt

data class Skin(
    val skinID: String,
    val playFabID: String,
    val skinResourcePatch: String,
    val skinImageWidth: UInt,
    val skinImageHeight: UInt,
    val skinData: ByteString,
    val animations: List<SkinAnimation>,
    val capeImageWidth: UInt,
    val capeImageHeight: UInt,
    val capeData: ByteString,
    val skinGeometry: String,
    val geometryDataMinEngineVersion: String,
    val animationData: ByteString,
    val capeID: String,
    val fullID: String,
    val armSize: String,
    val skinColor: String,
    val personaPieces: List<PersonaPiece>,
    val personaPieceTintColors: List<PersonaPieceTintColor>,
    val premiumSkin: Boolean,
    val personaSkin: Boolean,
    val personaCapeOnClassicSkin: Boolean,
    val primaryUser: Boolean,
    val overrideAppearance: Boolean,
) {
    companion object : ProtoCodec<Skin> {
        override fun serialize(value: Skin, stream: Sink) {
            Proto.String.serialize(value.skinID, stream)
            Proto.String.serialize(value.playFabID, stream)
            Proto.String.serialize(value.skinResourcePatch, stream)
            ProtoLE.UInt.serialize(value.skinImageWidth, stream)
            ProtoLE.UInt.serialize(value.skinImageHeight, stream)
            Proto.ByteString.serialize(value.skinData, stream)
            value.animations.let { animations ->
                ProtoLE.UInt.serialize(animations.size.toUInt(), stream)
                animations.forEach { SkinAnimation.serialize(it, stream) }
            }
            ProtoLE.UInt.serialize(value.capeImageWidth, stream)
            ProtoLE.UInt.serialize(value.capeImageHeight, stream)
            Proto.ByteString.serialize(value.capeData, stream)
            Proto.String.serialize(value.skinGeometry, stream)
            Proto.String.serialize(value.geometryDataMinEngineVersion, stream)
            Proto.ByteString.serialize(value.animationData, stream)
            Proto.String.serialize(value.capeID, stream)
            Proto.String.serialize(value.fullID, stream)
            Proto.String.serialize(value.armSize, stream)
            Proto.String.serialize(value.skinColor, stream)
            value.personaPieces.let { personaPieces ->
                ProtoLE.UInt.serialize(personaPieces.size.toUInt(), stream)
                personaPieces.forEach { PersonaPiece.serialize(it, stream) }
            }
            value.personaPieceTintColors.let { personaPieceTintColors ->
                ProtoLE.UInt.serialize(personaPieceTintColors.size.toUInt(), stream)
                personaPieceTintColors.forEach { PersonaPieceTintColor.serialize(it, stream) }
            }
            Proto.Boolean.serialize(value.premiumSkin, stream)
            Proto.Boolean.serialize(value.personaSkin, stream)
            Proto.Boolean.serialize(value.personaCapeOnClassicSkin, stream)
            Proto.Boolean.serialize(value.primaryUser, stream)
            Proto.Boolean.serialize(value.overrideAppearance, stream)
        }

        override fun deserialize(stream: Source): Skin {
            return Skin(
                skinID = Proto.String.deserialize(stream),
                playFabID = Proto.String.deserialize(stream),
                skinResourcePatch = Proto.String.deserialize(stream),
                skinImageWidth = ProtoLE.UInt.deserialize(stream),
                skinImageHeight = ProtoLE.UInt.deserialize(stream),
                skinData = Proto.ByteString.deserialize(stream),
                animations = List(ProtoLE.UInt.deserialize(stream).toInt()) {
                    SkinAnimation.deserialize(stream)
                },
                capeImageWidth = ProtoLE.UInt.deserialize(stream),
                capeImageHeight = ProtoLE.UInt.deserialize(stream),
                capeData = Proto.ByteString.deserialize(stream),
                skinGeometry = Proto.String.deserialize(stream),
                geometryDataMinEngineVersion = Proto.String.deserialize(stream),
                animationData = Proto.ByteString.deserialize(stream),
                capeID = Proto.String.deserialize(stream),
                fullID = Proto.String.deserialize(stream),
                armSize = Proto.String.deserialize(stream),
                skinColor = Proto.String.deserialize(stream),
                personaPieces = List(ProtoLE.UInt.deserialize(stream).toInt()) {
                    PersonaPiece.deserialize(stream)
                },
                personaPieceTintColors = List(ProtoLE.UInt.deserialize(stream).toInt()) {
                    PersonaPieceTintColor.deserialize(stream)
                },
                premiumSkin = Proto.Boolean.deserialize(stream),
                personaSkin = Proto.Boolean.deserialize(stream),
                personaCapeOnClassicSkin = Proto.Boolean.deserialize(stream),
                primaryUser = Proto.Boolean.deserialize(stream),
                overrideAppearance = Proto.Boolean.deserialize(stream),
            )
        }
    }
}