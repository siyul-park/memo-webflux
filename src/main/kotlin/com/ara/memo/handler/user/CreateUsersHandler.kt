package com.ara.memo.handler.user

import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.dto.user.payload.UserResponsePayload
import com.ara.memo.entity.user.User
import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.plugin.apply
import com.ara.memo.util.validation.ValidationPlugin
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class CreateUsersHandler(
    private val resource: UserResource,
    validator: Validator
) : Handler {
    private val validationPlugin = ValidationPlugin.of(validator)

    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val users = validateAndGetBody(request)
            .collectList()
            .flatMapMany(resource::createAll)

        return createResponse(users)
    }

    private fun validateAndGetBody(request: ServerRequest): Flux<User> {
        return request.bodyToFlux(UserCreatePayload::class.java)
            .apply(validationPlugin)
            .map(UserCreatePayload::toUser)
    }

    private fun createResponse(users: Flux<User>): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserResponsePayload.PublicProfile::class.java)
            .body(users.map { UserResponsePayload.from(it) }, UserResponsePayload::class.java)
    }
}