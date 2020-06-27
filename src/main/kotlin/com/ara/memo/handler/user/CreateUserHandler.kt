package com.ara.memo.handler.user

import com.ara.memo.dto.user.UserView
import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.entity.user.User
import com.ara.memo.handler.ResourceHandler
import com.ara.memo.service.user.UserResource
import com.ara.memo.util.plugin.apply
import com.ara.memo.util.validation.ValidationPlugin
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI
import javax.validation.Validator

@Component
class CreateUserHandler(
    private val resource: UserResource,
    validator: Validator
) : ResourceHandler<Mono<User>, Mono<User>>() {
    private val validationPlugin = ValidationPlugin.of(validator)

    override fun getBody(request: ServerRequest): Mono<User> {
        return request.bodyToMono(UserCreatePayload::class.java)
            .apply(validationPlugin)
            .map(UserCreatePayload::toUser)
    }

    override fun process(body: Mono<User>, request: ServerRequest): Mono<User> {
        return body.flatMap(resource::create)
    }

    override fun render(resource: Mono<User>, request: ServerRequest): Mono<ServerResponse> {
        return resource.flatMap {
            ServerResponse.created(URI.create("${request.uri()}/${it.id}"))
                .hint(Jackson2CodecSupport.JSON_VIEW_HINT, UserView.PublicProfile::class.java)
                .bodyValue(UserView.from(it))
        }
    }
}