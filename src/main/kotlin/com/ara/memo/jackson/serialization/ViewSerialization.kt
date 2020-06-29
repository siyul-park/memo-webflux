package com.ara.memo.jackson.serialization

import com.ara.memo.util.view.View
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class ViewSerialization(
    private val objectMapper: ObjectMapper
) : JsonSerializer<View<*>>() {
    override fun serialize(value: View<*>, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeObject(serialize(value))
    }

    @Cacheable("dynamic")
    fun serialize(value: View<*>): JsonNode {
        return objectMapper.readTree(
            if (value.view == null) {
                objectMapper.writeValueAsString(value.value)
            } else {
                objectMapper.writerWithView(value.view.java).writeValueAsString(value.value)
            }
        )
    }
}