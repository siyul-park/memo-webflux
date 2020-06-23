package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.dto.user.payload.UserUpdatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.route.PathDefinition
import com.ara.memo.service.user.UserService
import com.ara.memo.util.patch.PatchFactory
import com.ara.memo.util.uri.URIFactory
import com.ara.memo.util.validation.Checkpoint
import com.ara.memo.util.value.mapper.ValueMappers
import com.ara.memo.util.value.path.PathVariableExtractor
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.request.factory.RequestMappers
import com.ara.memo.util.view.mapper.response.factory.ResponseMappers
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler(
    private val service: UserService,
    private val checkpoint: Checkpoint,
    private val uriFactory: URIFactory,
    private val patchFactory: PatchFactory,
    private val valueMappers: ValueMappers
) {
    private val requestMappingInfoForCreate = MappingInfo.from(UserCreatePayload::class)
    private val requestMappingInfoForUpdate = MappingInfo.from(UserUpdatePayload::class)

    private val viewMappingInfoForPublic = MappingInfo.of(UserView::class, UserView.PublicProfile::class)

    private val requestMapperForCreate = RequestMappers.from(requestMappingInfoForCreate)
    private val responseMapperForCreate = ResponseMappers.from(viewMappingInfoForPublic) {
        ServerResponse.created(uriFactory.create(PathDefinition.users, it.id))
    }

    private val requestMapperForUpdate = RequestMappers.from(requestMappingInfoForUpdate)
    private val responseMapperForUpdate = ResponseMappers.from(viewMappingInfoForPublic) {
        ServerResponse.ok()
    }

    fun create(request: ServerRequest) = requestMapperForCreate.map(request)
        .flatMap { checkpoint.validate(it) }
        .flatMap { service.create(it.toUser()) }
        .flatMap { responseMapperForCreate.map(UserView.from(it)) }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val pathVariableExtractor = PathVariableExtractor(request.pathVariables(), valueMappers)
        val userId = pathVariableExtractor.extract("userId")

        return requestMapperForUpdate.map(request)
            .flatMap { checkpoint.validate(it) }
            .flatMap { service.updateById(userId, patchFactory.create(it, User::class)) }
            .flatMap { responseMapperForUpdate.map(UserView.from(it)) }
    }
}