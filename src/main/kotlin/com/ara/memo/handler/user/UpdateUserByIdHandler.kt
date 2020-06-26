package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserUpdatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.handler.Handler
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.patch.Patch
import com.ara.memo.util.patch.PatchFactory
import com.ara.memo.util.validation.ValidationSupporter
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UpdateUserByIdHandler(
    private val resource: UserResource,
    private val validationSupporter: ValidationSupporter,
    private val patchFactory: PatchFactory
) : Handler {
    override fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("userId")

        return validateAndGetPatch(request)
            .flatMap { resource.updateById(id, it) }
            .flatMap(this::createResponse)
    }

    private fun validateAndGetPatch(request: ServerRequest): Mono<Patch<User>> {
        return request.bodyToMono(UserUpdatePayload::class.java)
            .flatMap { validationSupporter.validate(it) }
            .map { patchFactory.create(it, User::class) }
    }

    private fun createResponse(user: User): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
            .bodyValue(UserView.from(user))
    }
}