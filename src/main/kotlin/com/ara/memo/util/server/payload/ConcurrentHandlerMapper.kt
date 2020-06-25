package com.ara.memo.util.server.payload

import com.ara.memo.util.server.payload.exception.NotFountHandlerException
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

class ConcurrentHandlerMapper<K: Any>: HandlerMapper<K> {
    private val map: MutableMap<K, (ServerRequest) -> Mono<ServerResponse>> = ConcurrentHashMap()

    override fun register(key: K, handler: (ServerRequest) -> Mono<ServerResponse>): HandlerMapper<K> {
        map[key] = handler
        return this
    }

    override fun get(key: K) = map[key] ?: throw NotFountHandlerException
}