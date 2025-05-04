package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.camera.aimassist.*

data class CameraAimAssistPresetsPacket(
    val categories: MutableList<CameraAimAssistCategories>,
    val presets: MutableList<CameraAimAssistPreset>,
    val operation: CameraAimAssistPresetsPacketOperation,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeArray(categories) { buf, categories ->
            this.writeCategories(
                buf,
                categories
            )
        }
        byteBuf.writeArray(presets) { buf, preset ->
            this.writeCameraAimAssist(
                buf,
                preset
            )
        }
        byteBuf.writeByte(operation.ordinal)
    }

    private fun writeCategories(byteBuf: ByteBuf, categories: CameraAimAssistCategories) {
        byteBuf.writeString(categories.identifier)
        byteBuf.writeArray(categories.categories) { buf, category ->
            this.writeCategory(
                buf,
                category
            )
        }
    }

    private fun writeCategory(byteBuf: ByteBuf, category: CameraAimAssistCategory) {
        byteBuf.writeString(category.name)
        writePriorities(byteBuf, category.priorities)
    }

    private fun writePriorities(byteBuf: ByteBuf, priorities: CameraAimAssistCategoryPriorities) {
        byteBuf.writeArray(priorities.entities.entries) { buf, priority ->
            this.writePriority(
                buf,
                priority
            )
        }
        byteBuf.writeArray(priorities.blocks.entries) { buf, priority ->
            this.writePriority(
                buf,
                priority
            )
        }
    }

    private fun writePriority(byteBuf: ByteBuf, priority: Map.Entry<String, Int>) {
        byteBuf.writeString(priority.key)
        byteBuf.writeIntLE(priority.value)
    }

    private fun writeCameraAimAssist(byteBuf: ByteBuf, preset: CameraAimAssistPreset) {
        byteBuf.writeString(preset.identifier)
        byteBuf.writeString(preset.categories)
        byteBuf.writeArray(preset.exclusionList) { str -> byteBuf.writeString(str) }
        byteBuf.writeArray(preset.liquidTargetingList) { str -> byteBuf.writeString(str) }
        byteBuf.writeArray(preset.itemSettings.entries) { buf, itemSetting ->
            this.writeItemSetting(
                buf,
                itemSetting
            )
        }
        byteBuf.writeOptional(preset.defaultItemSettings) { str -> byteBuf.writeString(str) }
        byteBuf.writeOptional(preset.handSettings) { str -> byteBuf.writeString(str) }
    }

    private fun writeItemSetting(byteBuf: ByteBuf, itemSetting: Map.Entry<String, String>) {
        byteBuf.writeString(itemSetting.key)
        byteBuf.writeString(itemSetting.value)
    }

    override fun pid(): Int {
        return ProtocolInfo.CAMERA_AIM_ASSIST_PRESETS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
