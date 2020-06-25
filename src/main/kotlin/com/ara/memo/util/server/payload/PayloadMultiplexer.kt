package com.ara.memo.util.server.payload

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

class PayloadMultiplexer<K : Any>(
    private val keyExtractor: (ServerRequest) -> Mono<K>
) : HandlerMapper<K> by ConcurrentHandlerMapper() {
    fun handle(request: ServerRequest) = keyExtractor(request)
        .map { get(it) }
        .flatMap { it(request) }
}