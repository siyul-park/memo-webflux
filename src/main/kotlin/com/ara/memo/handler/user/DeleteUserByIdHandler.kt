package com.ara.memo.handler.user

import com.ara.memo.service.user.UserResource
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DeleteUserByIdHandler(
    private val resource: UserResource
) : AbstractDeleteHandler() {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("userId")

        return resource.deleteById(userId)
            .then(createResponse())
    }
}