package com.ara.memo.handler.user

import com.ara.memo.dto.user.payload.UserResponsePayload
import com.ara.memo.dto.user.payload.UserUpdatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.jackson.model.JsonView
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.patch.JsonPatchFactory
import com.ara.memo.util.patch.Patch
import com.ara.memo.util.plugin.apply
import com.ara.memo.util.validation.ValidationPlugin
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class UpdateUserByIdHandler(
    private val resource: UserResource,
    private val patchFactory: JsonPatchFactory,
    validator: Validator
) : AbstractUpdateHandler() {
    private val validationPlugin = ValidationPlugin.of(validator)

    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("userId")
        val patch = validateAndGetPatch(request)
        val user = patch.flatMap { resource.updateById(id, it) }

        return createResponse(
            user.map {
                JsonView.of(UserResponsePayload.from(it), UserResponsePayload.PublicProfile::class)
            }
        )
    }

    private fun validateAndGetPatch(request: ServerRequest): Mono<Patch<User>> {
        return request.bodyToMono(UserUpdatePayload::class.java)
            .apply(validationPlugin)
            .map { patchFactory.create(it, User::class) }
    }
}