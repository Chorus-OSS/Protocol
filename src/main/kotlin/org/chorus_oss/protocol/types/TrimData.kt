package org.chorus_oss.protocol.types

import com.google.gson.JsonObject
import org.chorus_oss.chorus.utils.JSONUtils

object TrimData {
    var trimPatterns: List<TrimPattern> = emptyList()
    var trimMaterials: List<TrimMaterial> = emptyList()

    init {
        try {
            TrimData::class.java.classLoader.getResourceAsStream("trim_data.json").use { stream ->
                stream ?: throw Exception("trim_data.json could not be loaded")

                val obj = JSONUtils.from(stream, JsonObject::class.java)
                val l1 = mutableListOf<TrimPattern>()
                val l2 = mutableListOf<TrimMaterial>()
                for (e in obj.getAsJsonArray("patterns").asList()) {
                    val asJsonObject = e.asJsonObject
                    l1.add(TrimPattern(asJsonObject["itemName"].asString, asJsonObject["patternId"].asString))
                }
                for (e in obj.getAsJsonArray("materials").asList()) {
                    val asJsonObject = e.asJsonObject
                    l2.add(
                        TrimMaterial(
                            asJsonObject["materialId"].asString,
                            asJsonObject["color"].asString,
                            asJsonObject["itemName"].asString
                        )
                    )
                }
                trimPatterns = l1.toList()
                trimMaterials = l2.toList()
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
