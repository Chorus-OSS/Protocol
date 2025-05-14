package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.hud.HudElement
import org.chorus_oss.protocol.types.hud.HudVisibility

class SetHudPacket : Packet(id) {
    val elements: MutableSet<HudElement> = mutableSetOf()
    var visibility: HudVisibility? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeArray(this.elements) { buf, element ->
            buf.writeUnsignedVarInt(element.ordinal)
        }
        byteBuf.writeVarInt(visibility!!.ordinal)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_HUD
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SetHudPacket> {
        override fun decode(byteBuf: ByteBuf): SetHudPacket {
            val packet = SetHudPacket()

            packet.elements.clear()
            byteBuf.readArray(packet.elements) {
                HudElement.entries[it.readUnsignedVarInt()]
            }
            packet.visibility = HudVisibility.entries[byteBuf.readVarInt()]

            return packet
        }
    }
}
