package com.ara.memo.handler

import org.reactivestreams.Publisher
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import kotlin.reflect.KClass

abstract class Handler {
    protected fun <T : Any, V: Any> ServerRequest.bodyToMonoWithView(type: KClass<T>, view: KClass<V>) = body(
        BodyExtractors.toMono(type.java),
        mapOf(
            Jackson2CodecSupport.JSON_VIEW_HINT to view.java
        )
    )

    protected fun <P : Publisher<T>, T : Any, V: Any> ServerResponse.BodyBuilder.bodyWithView(element: P, type: KClass<T>, view: KClass<V>): Mono<ServerResponse> {
        return hint(Jackson2CodecSupport.JSON_VIEW_HINT, view.java)
            .body(
                BodyInserters.fromPublisher(element, type.java)
            )
    }
}