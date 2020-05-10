package com.ara.memo.handler

import com.ara.memo.service.user.UserService
import com.ara.memo.view.user.UserView
import org.springframework.http.MediaType
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler(
    private val service: UserService
) {
    fun create(request: ServerRequest): Mono<ServerResponse> {
        val uri = request.uri()
        val userView = request.bodyToMono(UserView::class.java)

        return ServerResponse.created(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.CreateResponseView::class.java)
            .body(
                BodyInserters.fromPublisher(
                    userView
                        .map { it.toUser() }
                        .flatMap(service::create)
                        .map { UserView.from(it) },
                    UserView::class.java
                )
            )
    }
}