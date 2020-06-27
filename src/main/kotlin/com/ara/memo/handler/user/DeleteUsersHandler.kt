package com.ara.memo.handler.user

import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DeleteUsersHandler(
    private val resource: UserResource
) : Handler {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        return resource.deleteAll()
            .then(ServerResponse.noContent().build())
    }
}