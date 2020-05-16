package com.ara.memo.contoller

import com.ara.memo.contoller.exception.BadRequest
import com.ara.memo.dto.token.GrantType
import com.ara.memo.dto.token.TokenRequest
import com.ara.memo.dto.user.UserRequest
import com.ara.memo.entity.user.User
import com.ara.memo.service.user.UserService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

@Api(value = "Token Controller")
@RestController
@RequestMapping("/token")
@ResponseBody
class TokenController(
    private val userService: UserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(request: ServerRequest) = request.bodyToMono(TokenRequest::class.java)
        .onErrorMap { BadRequest }
        .flatMap { when (it.grantType) {
            GrantType.PASSWORD -> createByPassword(request)
        } }

    private fun createByPassword(request: ServerRequest) = authorizeUser(request)

    private fun authorizeUser(request: ServerRequest) = request.body(
        BodyExtractors.toMono(UserRequest::class.java),
        mapOf(Jackson2CodecSupport.JSON_VIEW_HINT to UserRequest.Authorize::class.java)
    ).onErrorMap { BadRequest }.flatMap { userService.authorize(it.toUser()) }
}