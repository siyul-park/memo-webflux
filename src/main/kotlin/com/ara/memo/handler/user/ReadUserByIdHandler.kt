package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.entity.user.User
import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ReadUserByIdHandler(
    private val resource: UserResource
) : Handler {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("userId")

        return resource.findById(userId)
            .flatMap { createResponse(it) }
    }

    private fun createResponse(user: User): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class)
            .bodyValue(UserView.from(user))
    }
}