package com.ara.memo.handler.user

import com.ara.memo.dto.user.payload.UserResponsePayload
import com.ara.memo.handler.Handler
import com.ara.memo.jackson.model.JsonProjection
import com.ara.memo.jackson.model.JsonView
import org.reactivestreams.Publisher
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

abstract class AbstractReadUserHandler : Handler {
    protected fun createResponse(userPayload: Publisher<JsonProjection<JsonView<UserResponsePayload>>>): Mono<ServerResponse> {
        return ServerResponse.ok()
            .body(userPayload, JsonProjection::class.java)
    }
}