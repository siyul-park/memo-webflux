package com.ara.memo.handler.user

import com.ara.memo.dto.user.payload.UserResponsePayload
import com.ara.memo.handler.Handler
import com.ara.memo.jackson.model.JsonView
import org.reactivestreams.Publisher
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

abstract class AbstractUpdateHandler : Handler {
    protected fun createResponse(userPayload: Publisher<JsonView<UserResponsePayload>>): Mono<ServerResponse> {
        return ServerResponse.ok()
            .body(userPayload, JsonView::class.java)
    }
}