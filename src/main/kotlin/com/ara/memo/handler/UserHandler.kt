package com.ara.memo.handler

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.view.UserView
import com.ara.memo.route.UserRoutes
import com.ara.memo.service.user.UserService
import com.ara.memo.util.validation.Checkpoint
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.request.factory.RequestMappers
import com.ara.memo.util.view.mapper.response.factory.ResponseMappers
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import java.net.URI

private val requestMappingInfoForCreate = MappingInfo(UserRequest::class, UserRequest.Create::class)
private val viewMappingInfoForPublic = MappingInfo(UserView::class, UserView.PublicProfile::class)

private val requestMapperForCreate = RequestMappers.from(requestMappingInfoForCreate)
private val responseMapperForCreate = ResponseMappers.from(viewMappingInfoForPublic) {
    ServerResponse.created(URI.create("${UserRoutes.path}/${it.id}"))
}

@Component
class UserHandler(
    private val checkpoint: Checkpoint,
    private val service: UserService
) {
    fun create(request: ServerRequest) = requestMapperForCreate.map(request)
        .flatMap { checkpoint.validate(it, UserRequest.Create::class) }
        .flatMap { service.create(it.toUser()) }
        .flatMap { responseMapperForCreate.map(UserView.from(it)) }
}