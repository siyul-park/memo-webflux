package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.handler.Handler
import com.ara.memo.util.view.ViewProjection
import org.reactivestreams.Publisher
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

abstract class AbstractReadUserHandler : Handler {
    protected fun createResponse(userView: Publisher<ViewProjection<UserView>>): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
            .body(userView, ViewProjection::class.java)
    }
}