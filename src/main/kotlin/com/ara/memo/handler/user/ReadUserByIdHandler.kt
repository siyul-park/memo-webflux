package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.entity.user.User
import com.ara.memo.handler.ResourceHandler
import com.ara.memo.service.user.UserResource
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ReadUserByIdHandler(
    private val resource: UserResource
) : ResourceHandler<Unit, Mono<User>>() {
    override fun getBody(request: ServerRequest) {
    }

    override fun process(body: Unit, request: ServerRequest): Mono<User> {
        val id = request.pathVariable("userId")
        return resource.findById(id)
    }

    override fun render(resource: Mono<User>, request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
            .body(resource.map { UserView.from(it) }, UserView::class.java)
    }
}