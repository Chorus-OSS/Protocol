package org.chorus_oss.protocol.types


import org.chorus_oss.protocol.packets.UpdateAbilitiesPacket

data class SerializedAbilitiesData(
    val targetPlayerRawID: ActorUniqueID,
    val playerPermissions: PlayerPermission,
    val commandPermissions: CommandPermission,
    val layers: Array<AbilityLayer>
) {
    fun write(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.targetPlayerRawID)
        byteBuf.writeByte(this.playerPermissions.ordinal)
        byteBuf.writeByte(this.commandPermissions.ordinal)
        byteBuf.writeArray(this.layers) {
            UpdateAbilitiesPacket.writeAbilityLayer(byteBuf, it)
        }
    }
}
