package com.ara.memo.handler

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserService
import com.ara.memo.util.json.bodyWithJsonView
import com.ara.memo.util.validation.ExpandedValidator
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
    fun create(request: ServerRequest) = getUserRequest(request, UserRequest.Create::class)
        .flatMap { service.create(it.toUser()) }
        .flatMap {
            ServerResponse.created(URI.create("${request.uri()}/${it.id}"))
                .bodyWithUserView(
                    Mono.fromCallable { UserView.from(it) },
                    UserView.PublicProfile::class
                )
        }

    fun <T : Any> getUserRequest(request: ServerRequest, profile: KClass<T>) = request
        .bodyWithJsonView(UserRequest::class, profile)
        .flatMap { validator.validate(it, profile) }

    fun <T : Any> ServerResponse.BodyBuilder.bodyWithUserView(value: Mono<UserView>, profile: KClass<T>) = this.hint(
        Jackson2CodecSupport.JSON_VIEW_HINT, profile.java
    ).body(BodyInserters.fromPublisher(value, UserView::class.java))
}