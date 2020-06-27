package com.ara.memo.handler.user

import com.ara.memo.service.user.UserResource
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DeleteUserByNameHandler(
    private val resource: UserResource
) : AbstractDeleteHandler() {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val username = request.pathVariable("username")

        return resource.deleteByUsername(username)
            .then(createResponse())
    }
}