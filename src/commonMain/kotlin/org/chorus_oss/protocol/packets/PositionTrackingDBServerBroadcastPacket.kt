package org.chorus_oss.protocol.packets

import io.netty.buffer.ByteBufInputStream
import io.netty.handler.codec.EncoderException
import org.chorus_oss.chorus.math.BlockVector3
import org.chorus_oss.chorus.math.Vector3
import org.chorus_oss.chorus.nbt.tag.CompoundTag
import org.chorus_oss.chorus.nbt.tag.IntTag
import org.chorus_oss.chorus.nbt.tag.ListTag

import java.io.IOException

class PositionTrackingDBServerBroadcastPacket : DataPacket() {
    var action: Action? = null
    var trackingId = 0
        set(value) {
            field = value
            if (tag != null) {
                tag!!.putString("id", String.format("0x%08x", value))
            }
        }
    var tag: CompoundTag? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(action!!.ordinal.toByte().toInt())
        byteBuf.writeVarInt(trackingId)
        try {
            byteBuf.writeBytes(writeNetwork((if (tag != null) tag else CompoundTag())!!))
        } catch (e: IOException) {
            throw EncoderException(e)
        }
    }

    private fun requireTag(): CompoundTag? {
        if (tag == null) {
            tag = CompoundTag()
                .putByte("version", 1)
                .putString("id", String.format("0x%08x", trackingId))
        }
        return tag
    }

    var position: BlockVector3?
        get() {
            if (tag == null) {
                return null
            }
            val pos = tag!!.getList("pos", IntTag::class.java)
            if (pos.size() != 3) {
                return null
            }
            return BlockVector3(pos[0].data, pos[1].data, pos[2].data)
        }
        set(position) {
            setPosition(position!!.x, position.y, position.z)
        }

    fun setPosition(position: Vector3) {
        setPosition(position.floorX, position.floorY, position.floorZ)
    }

    fun setPosition(x: Int, y: Int, z: Int) {
        requireTag()!!.putList(
            "pos", ListTag<IntTag>()
                .add(IntTag(x))
                .add(IntTag(y))
                .add(IntTag(z))
        )
    }

    var status: Int
        get() {
            if (tag == null) {
                return 0
            }
            return tag!!.getByte("status").toInt()
        }
        set(status) {
            requireTag()!!.putByte("status", status)
        }

    var version: Int
        get() {
            if (tag == null) {
                return 0
            }
            return tag!!.getByte("version").toInt()
        }
        set(status) {
            requireTag()!!.putByte("version", status)
        }

    var dimension: Int
        get() {
            if (tag == null) {
                return 0
            }
            return tag!!.getByte("dim").toInt()
        }
        set(dimension) {
            requireTag()!!.putInt("dim", dimension)
        }

    enum class Action {
        UPDATE,
        DESTROY,
        NOT_FOUND
    }

    override fun pid(): Int {
        return ProtocolInfo.POS_TRACKING_SERVER_BROADCAST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PositionTrackingDBServerBroadcastPacket> {
        override fun decode(byteBuf: ByteBuf): PositionTrackingDBServerBroadcastPacket {
            val packet = PositionTrackingDBServerBroadcastPacket()

            packet.action = ACTIONS[byteBuf.readByte().toInt()]
            packet.trackingId = byteBuf.readVarInt()
            try {
                ByteBufInputStream(byteBuf).use { inputStream ->
                    packet.tag = readNetworkCompressed(inputStream)
                }
            } catch (e: IOException) {
                throw EncoderException(e)
            }

            return packet
        }

        private val ACTIONS = Action.entries.toTypedArray()
    }
}
