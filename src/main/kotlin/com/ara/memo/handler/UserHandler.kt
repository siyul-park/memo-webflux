package com.ara.memo.handler

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserService
import com.ara.memo.util.validation.Checkpoint
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.factory.ViewMappers
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class UserHandler(
    private val validator: Checkpoint,
    private val service: UserService
) {
    private val createUserRequestMappingInfo = MappingInfo(UserRequest::class, UserRequest.Create::class)
    private val publicUserProfileMappingInfo = MappingInfo(UserView::class, UserView.PublicProfile::class)

    fun create(request: ServerRequest) = ViewMappers.requestViewMapper(createUserRequestMappingInfo)
        .map(request)
        .flatMap { validator.validate(it, UserRequest.Create::class) }
        .flatMap { service.create(it.toUser()) }
        .flatMap {
            ViewMappers.responseViewMapper(publicUserProfileMappingInfo) {
                ServerResponse.created(URI.create("${request.uri()}/${it.id}"))
            }.map(Mono.fromCallable { UserView.from(it) })
        }
}