package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserUpdatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.patch.Patch
import com.ara.memo.util.patch.PatchFactory
import com.ara.memo.util.plugin.apply
import com.ara.memo.util.validation.ValidationPlugin
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class UpdateUserByNameHandler(
    private val resource: UserResource,
    private val patchFactory: PatchFactory,
    validator: Validator
) : Handler {
    private val validationPlugin = ValidationPlugin.of(validator)

    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val username = request.pathVariable("username")

        return validateAndGetPatch(request)
            .flatMap { resource.updateByUsername(username, it) }
            .flatMap(this::createResponse)
    }

    private fun validateAndGetPatch(request: ServerRequest): Mono<Patch<User>> {
        return request.bodyToMono(UserUpdatePayload::class.java)
            .apply(validationPlugin)
            .map { patchFactory.create(it, User::class) }
    }

    private fun createResponse(user: User): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
            .bodyValue(UserView.from(user))
    }
}