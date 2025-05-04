package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorUniqueID

data class BossEventPacket(
    var targetActorID: ActorUniqueID,
    var eventType: EventType,
    val eventData: EventType.Companion.EventData?,
) : DataPacket(), PacketEncoder {
    enum class EventType {
        ADD,
        PLAYER_ADDED,
        REMOVE,
        PLAYER_REMOVED,
        UPDATE_PERCENT,
        UPDATE_NAME,
        UPDATE_PROPERTIES,
        UPDATE_STYLE,
        QUERY;

        companion object {
            fun fromOrdinal(ordinal: Int): EventType {
                return entries.find { it.ordinal == ordinal }
                    ?: throw RuntimeException("Unknown BossEventUpdateType Ordinal: $ordinal")
            }

            interface EventData

            data class AddData(
                val name: String,
                val filteredName: String,
                val healthPercent: Float,
                val darkenScreen: Short,
                val color: Int,
                val overlay: Int,
            ) : EventData

            data class PlayerAddedData(
                val playerID: ActorUniqueID,
            ) : EventData

            data class PlayerRemovedData(
                val playerID: ActorUniqueID,
            ) : EventData

            data class UpdatePercentData(
                val healthPercent: Float,
            ) : EventData

            data class UpdateNameData(
                val name: String,
                val filteredName: String,
            ) : EventData

            data class UpdatePropertiesData(
                val darkenScreen: Short,
                val color: Int,
                val overlay: Int,
            ) : EventData

            data class UpdateStyleData(
                val color: Int,
                val overlay: Int,
            ) : EventData

            data class QueryData(
                val playerID: ActorUniqueID,
            ) : EventData
        }
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.targetActorID)
        byteBuf.writeUnsignedVarInt(this.eventType.ordinal)
        when (this.eventType) {
            EventType.ADD -> {
                val eventData = this.eventData as EventType.Companion.AddData
                byteBuf.writeString(eventData.name)
                byteBuf.writeString(eventData.filteredName)
                byteBuf.writeFloatLE(eventData.healthPercent)
                byteBuf.writeShort(eventData.darkenScreen.toInt())
                byteBuf.writeUnsignedVarInt(eventData.color)
                byteBuf.writeUnsignedVarInt(eventData.overlay)
            }

            EventType.PLAYER_ADDED -> {
                val eventData = this.eventData as EventType.Companion.PlayerAddedData
                byteBuf.writeActorUniqueID(eventData.playerID)
            }

            EventType.REMOVE -> {}

            EventType.PLAYER_REMOVED -> {
                val eventData = this.eventData as EventType.Companion.PlayerRemovedData
                byteBuf.writeActorUniqueID(eventData.playerID)
            }

            EventType.UPDATE_PERCENT -> {
                val eventData = this.eventData as EventType.Companion.UpdatePercentData
                byteBuf.writeFloatLE(eventData.healthPercent)
            }

            EventType.UPDATE_NAME -> {
                val eventData = this.eventData as EventType.Companion.UpdateNameData
                byteBuf.writeString(eventData.name)
                byteBuf.writeString(eventData.filteredName)
            }

            EventType.UPDATE_PROPERTIES -> {
                val eventData = this.eventData as EventType.Companion.UpdatePropertiesData
                byteBuf.writeShort(eventData.darkenScreen.toInt())
                byteBuf.writeUnsignedVarInt(eventData.color)
                byteBuf.writeUnsignedVarInt(eventData.overlay)
            }

            EventType.UPDATE_STYLE -> {
                val eventData = this.eventData as EventType.Companion.UpdateStyleData
                byteBuf.writeUnsignedVarInt(eventData.color)
                byteBuf.writeUnsignedVarInt(eventData.overlay)
            }

            EventType.QUERY -> {
                val eventData = this.eventData as EventType.Companion.QueryData
                byteBuf.writeActorUniqueID(eventData.playerID)
            }
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.BOSS_EVENT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<BossEventPacket> {
        override fun decode(byteBuf: ByteBuf): BossEventPacket {
            val eventType: EventType
            return BossEventPacket(
                targetActorID = byteBuf.readActorUniqueID(),
                eventType = EventType.fromOrdinal(byteBuf.readUnsignedVarInt()).also { eventType = it },
                eventData = when (eventType) {
                    EventType.ADD -> EventType.Companion.AddData(
                        name = byteBuf.readString(),
                        filteredName = byteBuf.readString(),
                        healthPercent = byteBuf.readFloatLE(),
                        darkenScreen = byteBuf.readShort(),
                        color = byteBuf.readUnsignedVarInt(),
                        overlay = byteBuf.readUnsignedVarInt(),
                    )

                    EventType.PLAYER_ADDED -> EventType.Companion.PlayerAddedData(
                        playerID = byteBuf.readActorUniqueID()
                    )

                    EventType.REMOVE -> null

                    EventType.PLAYER_REMOVED -> EventType.Companion.PlayerRemovedData(
                        playerID = byteBuf.readActorUniqueID()
                    )

                    EventType.UPDATE_PERCENT -> EventType.Companion.UpdatePercentData(
                        healthPercent = byteBuf.readFloatLE()
                    )

                    EventType.UPDATE_NAME -> EventType.Companion.UpdateNameData(
                        name = byteBuf.readString(),
                        filteredName = byteBuf.readString(),
                    )

                    EventType.UPDATE_PROPERTIES -> EventType.Companion.UpdatePropertiesData(
                        darkenScreen = byteBuf.readShort(),
                        color = byteBuf.readUnsignedVarInt(),
                        overlay = byteBuf.readUnsignedVarInt(),
                    )

                    EventType.UPDATE_STYLE -> EventType.Companion.UpdateStyleData(
                        color = byteBuf.readUnsignedVarInt(),
                        overlay = byteBuf.readUnsignedVarInt(),
                    )

                    EventType.QUERY -> EventType.Companion.QueryData(
                        playerID = byteBuf.readActorUniqueID(),
                    )
                }
            )
        }
    }
}
