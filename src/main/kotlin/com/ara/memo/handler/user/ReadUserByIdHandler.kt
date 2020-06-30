package com.ara.memo.handler.user

import com.ara.memo.dto.user.payload.UserResponsePayload
import com.ara.memo.jackson.model.JsonProjection
import com.ara.memo.jackson.model.JsonView
import com.ara.memo.service.user.UserResource
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ReadUserByIdHandler(
    private val resource: UserResource
) : AbstractReadUserHandler() {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("userId")
        val fields = request.queryParams()["fields"] ?: listOf()
        val user = resource.findById(userId)
        return createResponse(
            user.map { UserResponsePayload.from(it) }
                .map { JsonProjection.of(JsonView.of(it, UserResponsePayload.PublicProfile::class), fields) }
        )
    }
}