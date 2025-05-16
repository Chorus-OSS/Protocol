package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

abstract class ItemDescriptor {
    abstract val type: Type

    companion object : ProtoCodec<ItemDescriptor> {
        enum class Type {
            Invalid,
            Default,
            MoLang,
            ItemTag,
            Deferred,
            ComplexAlias;

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(
            value: ItemDescriptor,
            stream: Sink
        ) {
            Type.serialize(value.type, stream)
            when (value) {
                is InvalidItemDescriptor -> InvalidItemDescriptor.serialize(value, stream)
                is DefaultItemDescriptor -> DefaultItemDescriptor.serialize(value, stream)
                is MoLangItemDescriptor -> MoLangItemDescriptor.serialize(value, stream)
                is ItemTagItemDescriptor -> ItemTagItemDescriptor.serialize(value, stream)
                is DeferredItemDescriptor -> DeferredItemDescriptor.serialize(value, stream)
                is ComplexAliasItemDescriptor -> ComplexAliasItemDescriptor.serialize(value, stream)
            }
        }

        override fun deserialize(stream: Source): ItemDescriptor {
            val type = Type.deserialize(stream)
            return when (type) {
                Type.Invalid -> InvalidItemDescriptor.deserialize(stream)
                Type.Default -> DefaultItemDescriptor.deserialize(stream)
                Type.MoLang -> MoLangItemDescriptor.deserialize(stream)
                Type.ItemTag -> ItemTagItemDescriptor.deserialize(stream)
                Type.Deferred -> DeferredItemDescriptor.deserialize(stream)
                Type.ComplexAlias -> ComplexAliasItemDescriptor.deserialize(stream)
            }
        }
    }
}