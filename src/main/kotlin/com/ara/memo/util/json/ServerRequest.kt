package com.ara.memo.util.json

import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import kotlin.reflect.KClass

fun <T : Any, M : Any> ServerRequest.bodyWithJsonView(type: KClass<T>, mode: KClass<M>) = this.body(
    BodyExtractors.toMono(type.java),
    mapOf(
        Jackson2CodecSupport.JSON_VIEW_HINT to mode.java
    )
)