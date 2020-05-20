package com.ara.memo.util.json

import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import kotlin.reflect.KClass

fun <V : Any, T : Any> ServerResponse.BodyBuilder.bodyWithJsonView(value: Mono<V>, view: KClass<V>, profile: KClass<T>) = this.hint(
    Jackson2CodecSupport.JSON_VIEW_HINT, profile.java
).body(BodyInserters.fromPublisher(value, view.java))