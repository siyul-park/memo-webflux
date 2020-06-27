package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.plugin.apply
import com.ara.memo.util.validation.ValidationPlugin
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI
import javax.validation.Validator

@Component
class CreateUserHandler(
    private val resource: UserResource,
    validator: Validator
) : Handler {
    private val validationPlugin = ValidationPlugin.of(validator)

    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        return validateAndGetBody(request)
            .flatMap(resource::create)
            .flatMap { createResponse(it, request) }
    }

    private fun validateAndGetBody(request: ServerRequest): Mono<User> {
        return request.bodyToMono(UserCreatePayload::class.java)
            .apply(validationPlugin)
            .map(UserCreatePayload::toUser)
    }

    private fun createResponse(user: User, request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.created(URI.create("${request.uri()}/${user.id}"))
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
            .bodyValue(UserView.from(user))
    }
}