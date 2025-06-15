package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String


data class BookEditPacket(
    var action: Action,
    var bookSlot: Byte,
    val actionData: ActionData,
) : Packet(id) {
    enum class Action {
        ReplacePage,
        AddPage,
        DeletePage,
        SwapPages,
        Finalize;

        companion object : ProtoCodec<Action> {
            override fun serialize(value: Action, stream: Sink) {
                Proto.Byte.serialize(value.ordinal.toByte(), stream)
            }

            override fun deserialize(stream: Source): Action {
                return entries[Proto.Byte.deserialize(stream).toInt()]
            }
        }
    }

    interface ActionData
    data class ReplacePageData(
        val pageIndex: Byte,
        val text: String,
        val photoName: String,
    ) : ActionData

    data class AddPageData(
        val pageIndex: Byte,
        val text: String,
        val photoName: String,
    ) : ActionData

    data class DeletePageData(
        val pageIndex: Byte,
    ) : ActionData

    data class SwapPagesData(
        val pageIndexA: Byte,
        val pageIndexB: Byte,
    ) : ActionData

    data class FinalizeData(
        val title: String,
        val author: String,
        val xuid: String,
    ) : ActionData

    companion object : PacketCodec<BookEditPacket> {
        override val id: Int = 97

        override fun deserialize(stream: Source): BookEditPacket {
            val action: Action
            return BookEditPacket(
                action = Action.deserialize(stream).also { action = it },
                bookSlot = Proto.Byte.deserialize(stream),
                actionData = when (action) {
                    Action.ReplacePage -> ReplacePageData(
                        pageIndex = Proto.Byte.deserialize(stream),
                        text = Proto.String.deserialize(stream),
                        photoName = Proto.String.deserialize(stream),
                    )

                    Action.AddPage -> AddPageData(
                        pageIndex = Proto.Byte.deserialize(stream),
                        text = Proto.String.deserialize(stream),
                        photoName = Proto.String.deserialize(stream),
                    )

                    Action.DeletePage -> DeletePageData(
                        pageIndex = Proto.Byte.deserialize(stream),
                    )

                    Action.SwapPages -> SwapPagesData(
                        pageIndexA = Proto.Byte.deserialize(stream),
                        pageIndexB = Proto.Byte.deserialize(stream),
                    )

                    Action.Finalize -> FinalizeData(
                        title = Proto.String.deserialize(stream),
                        author = Proto.String.deserialize(stream),
                        xuid = Proto.String.deserialize(stream),
                    )
                }
            )
        }

        override fun serialize(value: BookEditPacket, stream: Sink) {
            Action.serialize(value.action, stream)
            Proto.Byte.serialize(value.bookSlot, stream)

            when (value.action) {
                Action.ReplacePage -> {
                    val replacePageData = value.actionData as ReplacePageData
                    Proto.Byte.serialize(replacePageData.pageIndex, stream)
                    Proto.String.serialize(replacePageData.text, stream)
                    Proto.String.serialize(replacePageData.photoName, stream)
                }

                Action.AddPage -> {
                    val addPageData = value.actionData as AddPageData
                    Proto.Byte.serialize(addPageData.pageIndex, stream)
                    Proto.String.serialize(addPageData.text, stream)
                    Proto.String.serialize(addPageData.photoName, stream)
                }

                Action.DeletePage -> {
                    val deletePageData = value.actionData as DeletePageData
                    Proto.Byte.serialize(deletePageData.pageIndex, stream)
                }

                Action.SwapPages -> {
                    val swapPagesData = value.actionData as SwapPagesData
                    Proto.Byte.serialize(swapPagesData.pageIndexA, stream)
                    Proto.Byte.serialize(swapPagesData.pageIndexB, stream)
                }

                Action.Finalize -> {
                    val finalizeData = value.actionData as FinalizeData
                    Proto.String.serialize(finalizeData.title, stream)
                    Proto.String.serialize(finalizeData.author, stream)
                    Proto.String.serialize(finalizeData.xuid, stream)
                }
            }
        }
    }
}
