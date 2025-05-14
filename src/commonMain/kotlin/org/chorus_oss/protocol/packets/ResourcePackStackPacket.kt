package org.chorus_oss.protocol.packets


import org.chorus_oss.chorus.resourcepacks.ResourcePack

class ResourcePackStackPacket : Packet(id) {
    var mustAccept: Boolean = false
    var behaviourPackStack: Array<ResourcePack> = emptyArray()
    var resourcePackStack: Array<ResourcePack> = emptyArray()
    val experiments: MutableList<ExperimentData> = mutableListOf()
    var gameVersion: String = "*"
    var isHasEditorPacks: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.mustAccept)

        byteBuf.writeUnsignedVarInt(behaviourPackStack.size)
        for (entry in this.behaviourPackStack) {
            byteBuf.writeString(entry.packId.toString())
            byteBuf.writeString(entry.packVersion)
            byteBuf.writeString("") // TODO: subpack name
        }

        byteBuf.writeUnsignedVarInt(resourcePackStack.size)
        for (entry in this.resourcePackStack) {
            byteBuf.writeString(entry.packId.toString())
            byteBuf.writeString(entry.packVersion)
            byteBuf.writeString("") // TODO: subpack name
        }

        byteBuf.writeString(this.gameVersion)
        byteBuf.writeIntLE(experiments.size) // Experiments length
        for (experimentData in this.experiments) {
            byteBuf.writeString(experimentData.name)
            byteBuf.writeBoolean(experimentData.enabled)
        }
        byteBuf.writeBoolean(true) // Were experiments previously toggled
        byteBuf.writeBoolean(isHasEditorPacks)
    }

    data class ExperimentData(
        var name: String,
        var enabled: Boolean,
    )

    override fun pid(): Int {
        return ProtocolInfo.RESOURCE_PACK_STACK_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
