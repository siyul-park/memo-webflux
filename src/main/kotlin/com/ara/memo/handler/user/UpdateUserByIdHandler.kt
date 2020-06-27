package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserUpdatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.patch.Patch
import com.ara.memo.util.patch.PatchFactory
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
    private val patchFactory: PatchFactory,
    validator: Validator
) : AbstractUpdateHandler() {
    private val validationPlugin = ValidationPlugin.of(validator)

    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("userId")
        val patch = validateAndGetPatch(request)
        val user = patch.flatMap { resource.updateById(id, it) }

        return createResponse(user.map { UserView.from(it) })
    }

    private fun validateAndGetPatch(request: ServerRequest): Mono<Patch<User>> {
        return request.bodyToMono(UserUpdatePayload::class.java)
            .apply(validationPlugin)
            .map { patchFactory.create(it, User::class) }
    }
}