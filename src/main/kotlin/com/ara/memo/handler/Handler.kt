package com.ara.memo.handler

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface Handler {
    fun handleRequest(request: ServerRequest): Mono<ServerResponse>
}