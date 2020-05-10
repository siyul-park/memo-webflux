package com.ara.memo.handler

import com.ara.memo.service.user.UserService
import com.ara.memo.view.user.UserView
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler(
    private val service: UserService
) : Handler() {
    fun create(request: ServerRequest): Mono<ServerResponse> {
        val uri = request.uri()
        val requestBody = request.bodyToMonoWithView(
            UserView::class,
            UserView.CreateRequestView::class
        )
        val user = requestBody.map { it.toUser() }
            .flatMap(service::create)

        return ServerResponse.created(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyWithView(
                user.map{ UserView.from(it) },
                UserView::class,
                UserView.CreateResponseView::class
            )
    }
}