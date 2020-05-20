package com.ara.memo.handler

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserService
import com.ara.memo.util.json.bodyWithJsonView
import com.ara.memo.util.validation.ExpandedValidator
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class UserHandler(
    private val validator: ExpandedValidator,
    private val service: UserService
) {
    fun create(request: ServerRequest): Mono<ServerResponse> {
        val userRequest = request
            .bodyWithJsonView(UserRequest::class, UserRequest.Create::class)
            .flatMap { validator.validate(it, UserRequest.Create::class) }

        return userRequest.flatMap { service.create(it.toUser()) }
            .flatMap {
                ServerResponse.created(URI.create("${request.uri()}/${it.id}"))
                    .bodyWithJsonView(
                        Mono.fromCallable { UserView.from(it) },
                        UserView::class,
                        UserView.PublicProfile::class
                    )
            }
    }
}