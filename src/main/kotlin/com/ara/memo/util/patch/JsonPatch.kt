package com.ara.memo.util.patch

import com.fasterxml.jackson.databind.ObjectMapper

class JsonPatch<T>(
    private val overrides: Any,
    private val objectMapper: ObjectMapper
): Patch<T> {
    override fun apply(obj: T): T {
        objectMapper.updateValue(obj, overrides)

        return obj
    }
}