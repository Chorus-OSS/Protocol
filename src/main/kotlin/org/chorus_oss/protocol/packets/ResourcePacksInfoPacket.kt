package org.chorus_oss.protocol.packets


import org.chorus_oss.chorus.resourcepacks.ResourcePack

import java.util.*


class ResourcePacksInfoPacket : DataPacket() {
    var isForcedToAccept: Boolean = false
    var hasAddonPacks: Boolean = false
    var isScriptingEnabled: Boolean = false

    var worldTemplateId: UUID? = null

    var worldTemplateVersion: String? = null

    var resourcePackEntries: Array<ResourcePack> = ResourcePack.EMPTY_ARRAY

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.isForcedToAccept)
        byteBuf.writeBoolean(this.hasAddonPacks)
        byteBuf.writeBoolean(this.isScriptingEnabled)
        byteBuf.writeUUID(worldTemplateId!!)
        byteBuf.writeString(worldTemplateVersion!!)
        this.encodePacks(byteBuf, this.resourcePackEntries, false)
    }

    private fun encodePacks(byteBuf: ByteBuf, packs: Array<ResourcePack>, behaviour: Boolean) {
        byteBuf.writeShortLE(packs.size)
        for (entry in packs) {
            byteBuf.writeUUID(entry.packId)
            byteBuf.writeString(entry.packVersion)
            byteBuf.writeLongLE(entry.packSize.toLong())
            byteBuf.writeString(entry.encryptionKey) // encryption key
            byteBuf.writeString("") // sub-pack name
            byteBuf.writeString(if (!entry.encryptionKey.isEmpty()) entry.packId.toString() else "") // content identity
            byteBuf.writeBoolean(false) // scripting
            byteBuf.writeBoolean(false) // isAddonPack
            byteBuf.writeString(entry.cdnUrl()) // cdnUrl
            if (!behaviour) {
                byteBuf.writeBoolean(false) // raytracing capable
            }
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.RESOURCE_PACKS_INFO_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
