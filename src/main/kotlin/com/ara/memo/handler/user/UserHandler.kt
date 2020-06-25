package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.dto.user.payload.UserUpdatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.patch.PatchFactory
import com.ara.memo.util.server.payload.PayloadExtractor
import com.ara.memo.util.server.Renderer
import com.ara.memo.util.validation.Checkpoint
import com.ara.memo.util.value.mapper.ValueMappers
import com.ara.memo.util.value.path.PathVariableExtractor
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class UserHandler(
    private val resource: UserResource,
    checkpoint: Checkpoint,
    private val patchFactory: PatchFactory,
    private val valueMappers: ValueMappers
) {
    private val payloadExtractorForCreate = PayloadExtractor.of(UserCreatePayload::class, checkpoint)
    private val payloadExtractorForUpdate = PayloadExtractor.of(UserUpdatePayload::class, checkpoint)

    private val renderer = Renderer.of(UserView::class, UserView.PublicProfile::class)

    fun create(request: ServerRequest): Mono<ServerResponse> {
        val payload = payloadExtractorForCreate.bodyToMono(request)
        val user = payload.flatMap { resource.create(it.toUser()) }

        return user.flatMap { renderer.renderCreatedResponse(
            Mono.fromCallable { UserView.from(it) },
            URI.create("${request.uri()}/${it.id}")
        ) }
    }

    fun readAll(request: ServerRequest): Mono<ServerResponse> {
        val users = resource.findAll()

        return renderer.renderOkResponse(users.map { UserView.from(it) })
    }

    fun updateById(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)

        val userId = pathVariableExtractor.extract("userId")
        val payload = payloadExtractorForUpdate.bodyToMono(request)

        val user = payload.flatMap { resource.updateById(userId, patchFactory.create(it, User::class)) }

        return renderer.renderOkResponse(user.map { UserView.from(it) })
    }

    fun updateByName(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)

        val username = pathVariableExtractor.extract("username")
        val payload = payloadExtractorForUpdate.bodyToMono(request)

        val user = payload.flatMap { resource.updateByUsername(username, patchFactory.create(it, User::class)) }

        return renderer.renderOkResponse(user.map { UserView.from(it) })
    }

    fun readById(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val userId = pathVariableExtractor.extract("userId")

        val user = resource.findById(userId)

        return renderer.renderOkResponse(user.map { UserView.from(it) })
    }

    fun readByName(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val username = pathVariableExtractor.extract("username")

        val user = resource.findByUsername(username)

        return renderer.renderOkResponse(user.map { UserView.from(it) })
    }

    fun deleteAll(request: ServerRequest): Mono<ServerResponse> {
        return resource.deleteAll()
            .then(renderer.renderNoContentResponse())
    }

    fun deleteById(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val userId = pathVariableExtractor.extract("userId")

        return resource.deleteByIdWhenExist(userId)
            .then(renderer.renderNoContentResponse())
    }

    fun deleteByName(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val username = pathVariableExtractor.extract("username")

        return resource.deleteByUsernameWhenExist(username)
            .then(renderer.renderNoContentResponse())
    }
}