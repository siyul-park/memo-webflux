package com.ara.memo.jackson.serialization

import com.ara.memo.jackson.model.JsonView
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.stereotype.Component

@Component
class JsonViewSerializer(
    private val objectMapper: ObjectMapper
) : JsonSerializer<JsonView<*>>() {
    override fun serialize(value: JsonView<*>, gen: JsonGenerator, serializers: SerializerProvider) {
        val jsonNode = objectMapper.readTree(
            if (value.view == null) {
                objectMapper.writeValueAsString(value.value)
            } else {
                objectMapper.writerWithView(value.view.java).writeValueAsString(value.value)
            }
        )
        gen.writeObject(jsonNode)
    }
}