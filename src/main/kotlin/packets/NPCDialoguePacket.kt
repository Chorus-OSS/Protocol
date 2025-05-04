/*
 * https://PowerNukkit.org - The Nukkit you know but Powerful!
 * Copyright (C) 2021  José Roberto de Araújo Júnior
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

class NPCDialoguePacket : DataPacket() {
    @JvmField
    var runtimeEntityId: Long = 0

    @JvmField
    var action: NPCDialogAction = NPCDialogAction.OPEN

    @JvmField
    var dialogue: String = "" //content

    @JvmField
    var sceneName: String = ""

    @JvmField
    var npcName: String = ""

    @JvmField
    var actionJson: String = ""

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeLongLE(runtimeEntityId)
        byteBuf.writeVarInt(action.ordinal)
        byteBuf.writeString(dialogue)
        byteBuf.writeString(sceneName)
        byteBuf.writeString(npcName)
        byteBuf.writeString(actionJson)
    }


    enum class NPCDialogAction {
        OPEN,
        CLOSE
    }

    override fun pid(): Int {
        return ProtocolInfo.NPC_DIALOGUE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<NPCDialoguePacket> {
        override fun decode(byteBuf: ByteBuf): NPCDialoguePacket {
            val packet = NPCDialoguePacket()

            packet.runtimeEntityId = byteBuf.readLongLE()
            packet.action = ACTIONS[byteBuf.readVarInt()]
            packet.dialogue = byteBuf.readString()
            packet.sceneName = byteBuf.readString()
            packet.npcName = byteBuf.readString()
            packet.actionJson = byteBuf.readString()

            return packet
        }

        private val ACTIONS = NPCDialogAction.entries.toTypedArray()
    }
}
