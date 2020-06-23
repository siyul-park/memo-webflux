package com.ara.memo.util.json

import org.springframework.http.codec.json.Jackson2CodecSupport
import kotlin.reflect.KClass

class JacksonViewHintPatch<H : Any>(
    private val viewHint: KClass<H>
) {
    fun <T, R> apply(target: T, hint: T.(key: String, value: Any) -> R) = hint(target, Jackson2CodecSupport.JSON_VIEW_HINT, viewHint)
}