package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserResource
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ReadUsersHandler(
    private val resource: UserResource
) : AbstractReadUserHandler() {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val users = resource.findAll()
        return createResponse(users.map { UserView.from(it) })
    }
}