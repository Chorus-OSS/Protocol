package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

data class BookEditPacket(
    var action: Action,
    var bookSlot: Byte,
    val actionData: ActionData,
) : DataPacket() {
    enum class Action {
        REPLACE_PAGE,
        ADD_PAGE,
        DELETE_PAGE,
        SWAP_PAGES,
        FINALIZE
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

    override fun pid(): Int {
        return ProtocolInfo.BOOK_EDIT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<BookEditPacket> {
        override fun decode(byteBuf: ByteBuf): BookEditPacket {
            val action: Action
            return BookEditPacket(
                action = Action.entries[byteBuf.readByte().toInt()].also { action = it },
                bookSlot = byteBuf.readByte(),
                actionData = when (action) {
                    Action.REPLACE_PAGE -> ReplacePageData(
                        pageIndex = byteBuf.readByte(),
                        text = byteBuf.readString(),
                        photoName = byteBuf.readString(),
                    )

                    Action.ADD_PAGE -> AddPageData(
                        pageIndex = byteBuf.readByte(),
                        text = byteBuf.readString(),
                        photoName = byteBuf.readString(),
                    )

                    Action.DELETE_PAGE -> DeletePageData(
                        pageIndex = byteBuf.readByte(),
                    )

                    Action.SWAP_PAGES -> SwapPagesData(
                        pageIndexA = byteBuf.readByte(),
                        pageIndexB = byteBuf.readByte(),
                    )

                    Action.FINALIZE -> FinalizeData(
                        title = byteBuf.readString(),
                        author = byteBuf.readString(),
                        xuid = byteBuf.readString(),
                    )
                }
            )
        }
    }
}
