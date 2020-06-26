package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.entity.user.User
import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ReadUsersHandler(
    private val resource: UserResource
) : Handler {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        return createResponse(resource.findAll())
    }

    private fun createResponse(users: Flux<User>): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
            .body(users.map { UserView.from(it) })
    }
}