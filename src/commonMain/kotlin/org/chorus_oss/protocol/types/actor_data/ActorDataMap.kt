package org.chorus_oss.protocol.types.actor_data

import kotlinx.io.Buffer
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.shared.types.IVector3
import org.chorus_oss.protocol.shared.types.Vector3f

class ActorDataMap() : MutableMap<ActorDataKey, Any> {
    private val map = mutableMapOf<ActorDataKey, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(key: ActorDataType<T>): T? {
        return map[key.key] as T?
    }

    @Deprecated("Unsafe")
    override fun get(key: ActorDataKey): Any? {
        return map[key]
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> put(key: ActorDataType<T>, value: T): T? {
        return map.put(key.key, value) as? T?
    }

    @Deprecated("Unsafe")
    override fun put(key: ActorDataKey, value: Any): Any? {
        return map.put(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> remove(key: ActorDataType<T>): T? {
        return map.remove(key.key) as? T?
    }

    @Deprecated("Unsafe")
    override fun remove(key: ActorDataKey): Any? {
        return map.remove(key)
    }

    override val entries: MutableSet<MutableMap.MutableEntry<ActorDataKey, Any>>
        get() = map.entries

    override val keys: MutableSet<ActorDataKey>
        get() = map.keys

    override val size: Int
        get() = map.size

    override val values: MutableCollection<Any>
        get() = map.values

    override fun clear() {
        map.clear()
    }

    override fun isEmpty(): Boolean {
        return map.isEmpty()
    }

    override fun putAll(from: Map<out ActorDataKey, Any>) {
        map.putAll(from)
    }

    override fun containsValue(value: Any): Boolean {
        return map.containsValue(value)
    }

    override fun containsKey(key: ActorDataKey): Boolean {
        return map.containsKey(key)
    }

    companion object : ProtoCodec<ActorDataMap> {
        override fun serialize(value: ActorDataMap, stream: Buffer) {
            ProtoVAR.UInt.serialize(value.size.toUInt(), stream)
            for ((type, data) in value.entries) {
                ActorDataKey.serialize(type, stream)

                val format = ActorDataFormat.from(data)
                ActorDataFormat.serialize(format, stream)

                when (format) {
                    ActorDataFormat.BYTE -> Proto.Byte.serialize(data as Byte, stream)
                    ActorDataFormat.SHORT -> ProtoLE.Short.serialize(data as Short, stream)
                    ActorDataFormat.INT -> ProtoVAR.Int.serialize(data as Int, stream)
                    ActorDataFormat.FLOAT -> ProtoLE.Float.serialize(data as Float, stream)
                    ActorDataFormat.STRING -> Proto.String.serialize(data as String, stream)
                    ActorDataFormat.NBT -> Tag.serialize(data as Tag, stream, TagSerialization.NetLE, isRoot = true)
                    ActorDataFormat.VECTOR3I -> {
                        val vec = data as IVector3
                        ProtoVAR.Int.serialize(vec.x, stream)
                        ProtoVAR.Int.serialize(vec.y, stream)
                        ProtoVAR.Int.serialize(vec.z, stream)
                    }
                    ActorDataFormat.LONG -> ProtoVAR.Long.serialize(data as Long, stream)
                    ActorDataFormat.VECTOR3F -> Vector3f.serialize(data as Vector3f, stream)
                }
            }
        }

        override fun deserialize(stream: Buffer): ActorDataMap {
            val dataMap = ActorDataMap()
            val size = ProtoVAR.UInt.deserialize(stream).toInt()
            for (i in 0 until size) {
                val key = ActorDataKey.deserialize(stream)
                val format = ActorDataFormat.deserialize(stream)
                val data: Any = when (format) {
                    ActorDataFormat.BYTE -> Proto.Byte.deserialize(stream)
                    ActorDataFormat.SHORT -> ProtoLE.Short.deserialize(stream)
                    ActorDataFormat.INT -> ProtoVAR.Int.deserialize(stream)
                    ActorDataFormat.FLOAT -> ProtoLE.Float.deserialize(stream)
                    ActorDataFormat.STRING -> Proto.String.deserialize(stream)
                    ActorDataFormat.NBT -> Tag.deserialize(stream, TagSerialization.NetLE)
                    ActorDataFormat.VECTOR3I -> IVector3(
                        x = ProtoVAR.Int.deserialize(stream),
                        y = ProtoVAR.Int.deserialize(stream),
                        z = ProtoVAR.Int.deserialize(stream),
                    )
                    ActorDataFormat.LONG -> ProtoVAR.Long.deserialize(stream)
                    ActorDataFormat.VECTOR3F -> Vector3f.deserialize(stream)
                }

                dataMap[key] = data
            }

            return dataMap
        }
    }
}
