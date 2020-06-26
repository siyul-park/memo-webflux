package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.validation.ValidationSupporter
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class CreateUserHandler(
    private val resource: UserResource,
    private val validationSupporter: ValidationSupporter
) : Handler {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        return validateAndGetUser(request)
            .flatMap(resource::create)
            .flatMap { createResponse(it, request) }
    }

    private fun validateAndGetUser(request: ServerRequest): Mono<User> {
        return request.bodyToMono(UserCreatePayload::class.java)
            .flatMap { validationSupporter.validate(it) }
            .map(UserCreatePayload::toUser)
    }

    private fun createResponse(user: User, request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.created(URI.create("${request.uri()}/${user.id}"))
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
            .bodyValue(UserView.from(user))
    }
}