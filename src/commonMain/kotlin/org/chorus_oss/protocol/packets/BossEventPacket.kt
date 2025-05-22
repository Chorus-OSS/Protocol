package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.types.ActorUniqueID

data class BossEventPacket(
    var targetActorID: ActorUniqueID,
    var eventType: EventType,
    val eventData: EventType.Companion.EventData?,
) : Packet(id) {
    companion object : PacketCodec<BossEventPacket> {
        init { PacketRegistry.register(this) }

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

            companion object : ProtoCodec<EventType> {
                override fun serialize(value: EventType, stream: Sink) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): EventType {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }

                interface EventData

                data class AddData(
                    val name: String,
                    val filteredName: String,
                    val healthPercent: Float,
                    val darkenScreen: UShort,
                    val color: UInt,
                    val overlay: UInt,
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
                    val darkenScreen: UShort,
                    val color: UInt,
                    val overlay: UInt,
                ) : EventData

                data class UpdateStyleData(
                    val color: UInt,
                    val overlay: UInt,
                ) : EventData

                data class QueryData(
                    val playerID: ActorUniqueID,
                ) : EventData
            }
        }

        override val id: Int
            get() = ProtocolInfo.BOSS_EVENT_PACKET

        override fun deserialize(stream: Source): BossEventPacket {
            val eventType: EventType
            return BossEventPacket(
                targetActorID = ActorUniqueID.deserialize(stream),
                eventType = EventType.deserialize(stream).also { eventType = it },
                eventData = when (eventType) {
                    EventType.ADD -> EventType.Companion.AddData(
                        name = Proto.String.deserialize(stream),
                        filteredName = Proto.String.deserialize(stream),
                        healthPercent = ProtoLE.Float.deserialize(stream),
                        darkenScreen = ProtoLE.UShort.deserialize(stream),
                        color = ProtoVAR.UInt.deserialize(stream),
                        overlay = ProtoVAR.UInt.deserialize(stream),
                    )

                    EventType.PLAYER_ADDED -> EventType.Companion.PlayerAddedData(
                        playerID = ActorUniqueID.deserialize(stream)
                    )

                    EventType.REMOVE -> null

                    EventType.PLAYER_REMOVED -> EventType.Companion.PlayerRemovedData(
                        playerID = ActorUniqueID.deserialize(stream)
                    )

                    EventType.UPDATE_PERCENT -> EventType.Companion.UpdatePercentData(
                        healthPercent = ProtoLE.Float.deserialize(stream)
                    )

                    EventType.UPDATE_NAME -> EventType.Companion.UpdateNameData(
                        name = Proto.String.deserialize(stream),
                        filteredName = Proto.String.deserialize(stream),
                    )

                    EventType.UPDATE_PROPERTIES -> EventType.Companion.UpdatePropertiesData(
                        darkenScreen = ProtoLE.UShort.deserialize(stream),
                        color = ProtoVAR.UInt.deserialize(stream),
                        overlay = ProtoVAR.UInt.deserialize(stream),
                    )

                    EventType.UPDATE_STYLE -> EventType.Companion.UpdateStyleData(
                        color = ProtoVAR.UInt.deserialize(stream),
                        overlay = ProtoVAR.UInt.deserialize(stream),
                    )

                    EventType.QUERY -> EventType.Companion.QueryData(
                        playerID = ActorUniqueID.deserialize(stream),
                    )
                }
            )
        }

        override fun serialize(value: BossEventPacket, stream: Sink) {
            ActorUniqueID.serialize(value.targetActorID, stream)
            EventType.serialize(value.eventType, stream)

            when (value.eventType) {
                EventType.ADD -> {
                    val addData = value.eventData as EventType.Companion.AddData
                    Proto.String.serialize(addData.name, stream)
                    Proto.String.serialize(addData.filteredName, stream)
                    ProtoLE.Float.serialize(addData.healthPercent, stream)
                    ProtoLE.UShort.serialize(addData.darkenScreen, stream)
                    ProtoVAR.UInt.serialize(addData.color, stream)
                    ProtoVAR.UInt.serialize(addData.overlay, stream)
                }

                EventType.PLAYER_ADDED -> {
                    val playerAddedData = value.eventData as EventType.Companion.PlayerAddedData
                    ActorUniqueID.serialize(playerAddedData.playerID, stream)
                }

                EventType.REMOVE -> Unit
                EventType.PLAYER_REMOVED -> {
                    val playerRemovedData = value.eventData as EventType.Companion.PlayerRemovedData
                    ActorUniqueID.serialize(playerRemovedData.playerID, stream)
                }

                EventType.UPDATE_PERCENT -> {
                    val updatePercentData = value.eventData as EventType.Companion.UpdatePercentData
                    ProtoLE.Float.serialize(updatePercentData.healthPercent, stream)
                }

                EventType.UPDATE_NAME -> {
                    val updateNameData = value.eventData as EventType.Companion.UpdateNameData
                    Proto.String.serialize(updateNameData.name, stream)
                    Proto.String.serialize(updateNameData.filteredName, stream)
                }

                EventType.UPDATE_PROPERTIES -> {
                    val updatePropertiesData = value.eventData as EventType.Companion.UpdatePropertiesData
                    ProtoLE.UShort.serialize(updatePropertiesData.darkenScreen, stream)
                    ProtoVAR.UInt.serialize(updatePropertiesData.color, stream)
                    ProtoVAR.UInt.serialize(updatePropertiesData.overlay, stream)
                }

                EventType.UPDATE_STYLE -> {
                    val updateStyleData = value.eventData as EventType.Companion.UpdateStyleData
                    ProtoVAR.UInt.serialize(updateStyleData.color, stream)
                    ProtoVAR.UInt.serialize(updateStyleData.overlay, stream)
                }

                EventType.QUERY -> {
                    val queryData = value.eventData as EventType.Companion.QueryData
                    ActorUniqueID.serialize(queryData.playerID, stream)
                }
            }
        }
    }
}
