package com.ara.memo.handler

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

abstract class ResourceHandler<B : Any, R : Any> : Handler {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        return render(process(getBody(request), request), request)
    }

    protected abstract fun getBody(request: ServerRequest): B
    protected abstract fun process(body: B, request: ServerRequest): R
    protected abstract fun render(resource: R, request: ServerRequest): Mono<ServerResponse>
}