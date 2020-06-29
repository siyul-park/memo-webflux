package com.ara.memo.util.patch

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class JsonPatchFactory(
    private val objectMapper: ObjectMapper
) {
    fun <O : Any, T : Any> create(overrides: O, type: KClass<T>) = JsonPatch<T>(overrides, objectMapper)
}