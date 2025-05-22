package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String


data class BookEditPacket(
    var action: Action,
    var bookSlot: Byte,
    val actionData: ActionData,
) : Packet(id) {
    enum class Action {
        REPLACE_PAGE,
        ADD_PAGE,
        DELETE_PAGE,
        SWAP_PAGES,
        FINALIZE;

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
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.BOOK_EDIT_PACKET

        override fun deserialize(stream: Source): BookEditPacket {
            val action: Action
            return BookEditPacket(
                action = Action.deserialize(stream).also { action = it },
                bookSlot = Proto.Byte.deserialize(stream),
                actionData = when (action) {
                    Action.REPLACE_PAGE -> ReplacePageData(
                        pageIndex = Proto.Byte.deserialize(stream),
                        text = Proto.String.deserialize(stream),
                        photoName = Proto.String.deserialize(stream),
                    )

                    Action.ADD_PAGE -> AddPageData(
                        pageIndex = Proto.Byte.deserialize(stream),
                        text = Proto.String.deserialize(stream),
                        photoName = Proto.String.deserialize(stream),
                    )

                    Action.DELETE_PAGE -> DeletePageData(
                        pageIndex = Proto.Byte.deserialize(stream),
                    )

                    Action.SWAP_PAGES -> SwapPagesData(
                        pageIndexA = Proto.Byte.deserialize(stream),
                        pageIndexB = Proto.Byte.deserialize(stream),
                    )

                    Action.FINALIZE -> FinalizeData(
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
                Action.REPLACE_PAGE -> {
                    val replacePageData = value.actionData as ReplacePageData
                    Proto.Byte.serialize(replacePageData.pageIndex, stream)
                    Proto.String.serialize(replacePageData.text, stream)
                    Proto.String.serialize(replacePageData.photoName, stream)
                }

                Action.ADD_PAGE -> {
                    val addPageData = value.actionData as AddPageData
                    Proto.Byte.serialize(addPageData.pageIndex, stream)
                    Proto.String.serialize(addPageData.text, stream)
                    Proto.String.serialize(addPageData.photoName, stream)
                }

                Action.DELETE_PAGE -> {
                    val deletePageData = value.actionData as DeletePageData
                    Proto.Byte.serialize(deletePageData.pageIndex, stream)
                }

                Action.SWAP_PAGES -> {
                    val swapPagesData = value.actionData as SwapPagesData
                    Proto.Byte.serialize(swapPagesData.pageIndexA, stream)
                    Proto.Byte.serialize(swapPagesData.pageIndexB, stream)
                }

                Action.FINALIZE -> {
                    val finalizeData = value.actionData as FinalizeData
                    Proto.String.serialize(finalizeData.title, stream)
                    Proto.String.serialize(finalizeData.author, stream)
                    Proto.String.serialize(finalizeData.xuid, stream)
                }
            }
        }
    }
}
