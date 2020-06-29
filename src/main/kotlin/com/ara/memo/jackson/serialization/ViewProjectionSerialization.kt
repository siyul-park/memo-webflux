package com.ara.memo.jackson.serialization

import com.ara.memo.util.view.ViewProjection
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ViewProjectionSerialization(
    private val objectMapper: ObjectMapper
) : JsonSerializer<ViewProjection<*>>() {
    override fun serialize(value: ViewProjection<*>, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeObject(serialize(value))
    }

    @Cacheable("dynamic")
    fun serialize(value: ViewProjection<*>): ConcurrentHashMap<String, Any> {
        val jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(value.view))
        val result = objectMapper.updateValue(ConcurrentHashMap<String, Any>(), jsonNode)

        result.keys.forEach { if (!value.fields.contains(it)) result.remove(it) }

        return result
    }
}