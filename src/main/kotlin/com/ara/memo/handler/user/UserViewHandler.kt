package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.entity.user.User
import com.ara.memo.handler.ResourceHandler
import org.reactivestreams.Publisher
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import kotlin.reflect.KClass

abstract class UserViewHandler<B : Any, R : Publisher<User>>(
    private val view: KClass<*>
) : ResourceHandler<B, R>() {
    override fun render(resource: R, request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, view.java)
            .body(toUserView(resource), UserView::class.java)
    }

    protected abstract fun toUserView(resource: R): Publisher<UserView>
}