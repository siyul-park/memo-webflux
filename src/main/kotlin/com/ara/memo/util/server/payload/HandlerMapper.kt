package com.ara.memo.util.server.payload

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface HandlerMapper<K: Any> {
    fun register(key: K, handler: (ServerRequest) -> Mono<ServerResponse>): HandlerMapper<K>

    fun get(key: K): (ServerRequest) -> Mono<ServerResponse>
}