package com.ara.memo.handler.user

import com.ara.memo.handler.Handler
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

abstract class AbstractDeleteHandler : Handler {
    protected fun createResponse(): Mono<ServerResponse> {
        return ServerResponse.noContent().build()
    }
}