package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.dto.user.payload.UserUpdatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.service.user.UserService
import com.ara.memo.util.patch.PatchFactory
import com.ara.memo.util.server.Viewer
import com.ara.memo.util.server.bodyToMono
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
    private val service: UserService,
    private val checkpoint: Checkpoint,
    private val patchFactory: PatchFactory,
    private val valueMappers: ValueMappers
) {
    private val viewer = Viewer(UserView::class, UserView.PublicProfile::class)

    fun create(request: ServerRequest) = request.bodyToMono(UserCreatePayload::class)
        .flatMap { checkpoint.validate(it) }
        .flatMap { service.create(it.toUser()) }
        .flatMap {
            viewer.renderCreatedResponse(
                Mono.fromCallable { UserView.from(it) },
                URI.create("${request.uri()}/${it.id}")
            )
        }

    fun readAll(request: ServerRequest) = viewer.renderOkResponse(
        service.findAll().map { UserView.from(it) }
    )

    fun deleteAll(request: ServerRequest) = service.deleteAll()
        .flatMap { ServerResponse.noContent().build() }

    fun updateById(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val userId = pathVariableExtractor.extract("userId")

        return request.bodyToMono(UserUpdatePayload::class)
            .flatMap { checkpoint.validate(it) }
            .flatMap { service.updateById(userId, patchFactory.create(it, User::class)) }
            .flatMap { viewer.renderOkResponse(Mono.fromCallable { UserView.from(it) }) }
    }

    fun updateByName(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val username = pathVariableExtractor.extract("username")

        return request.bodyToMono(UserUpdatePayload::class)
            .flatMap { checkpoint.validate(it) }
            .flatMap { service.updateByUsername(username, patchFactory.create(it, User::class)) }
            .flatMap { viewer.renderOkResponse(Mono.fromCallable { UserView.from(it) }) }
    }

    fun readById(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val userId = pathVariableExtractor.extract("userId")

        return viewer.renderOkResponse(
            service.findById(userId).map { UserView.from(it) }
        )
    }

    fun readByName(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val username = pathVariableExtractor.extract("username")

        return viewer.renderOkResponse(
            service.findByUsername(username).map { UserView.from(it) }
        )
    }

    fun deleteById(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val userId = pathVariableExtractor.extract("userId")

        return service.deleteByIdWhenExist(userId)
            .flatMap { viewer.renderNoContentResponse() }
    }

    fun deleteByName(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val username = pathVariableExtractor.extract("username")

        return service.deleteByIdWhenExist(username)
            .flatMap { viewer.renderNoContentResponse() }
    }
}