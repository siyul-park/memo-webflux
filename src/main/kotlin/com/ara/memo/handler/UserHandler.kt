package com.ara.memo.handler

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI
import javax.validation.Validator

@Component
class UserHandler(
    validator: Validator,
    private val service: UserService
) : ResourceHandler<UserRequest, UserView>(
    validator,
    UserRequest::class, UserView::class
) {
    fun create(request: ServerRequest): Mono<ServerResponse> {
        val userRequest = request
            .bodyWithJsonView(UserRequest.Create::class)
            .flatMap { valid(it, UserRequest.Create::class) }

        return userRequest.flatMap { service.create(it.toUser()) }
            .flatMap {
                ServerResponse.created(URI.create("${request.uri()}/${it.id}"))
                    .bodyWithJsonView(Mono.fromCallable { UserView.from(it) }, UserView.PublicProfile::class)
            }
    }
}