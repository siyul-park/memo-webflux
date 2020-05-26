package com.ara.memo.handler

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserService
import com.ara.memo.util.json.bodyWithJsonView
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper
import com.ara.memo.util.view.mapper.ResponseViewMapper
import com.ara.memo.util.validation.ExpandedValidator
import com.ara.memo.util.view.mapper.factory.ViewMappers
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI
import kotlin.reflect.KClass

@Component
class UserHandler(
    private val validator: ExpandedValidator,
    private val service: UserService
) {
    private val createUserRequestMappingInfo = MappingInfo(UserRequest::class, UserRequest.Create::class)
    private val publicUserProfileMappingInfo = MappingInfo(UserView::class, UserView.PublicProfile::class)

    fun create(request: ServerRequest) = ViewMappers.createRequestViewMapper(createUserRequestMappingInfo)
        .map(request)
        .flatMap { validator.validate(it, UserRequest.Create::class) }
        .flatMap { service.create(it.toUser()) }
        .flatMap {
            ViewMappers.createResponseViewMapper(publicUserProfileMappingInfo) {
                ServerResponse.created(URI.create("${request.uri()}/${it.id}"))
            }.map(Mono.fromCallable { UserView.from(it) })
        }
}