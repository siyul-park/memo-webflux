package com.ara.memo.contoller

import com.ara.memo.dto.token.TokenRequestInfo
import com.ara.memo.dto.token.GrantType
import com.ara.memo.dto.token.TokenView
import com.ara.memo.dto.user.UserAuthorizeInfo
import com.ara.memo.service.token.TokenService
import com.ara.memo.service.user.UserService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

@Api(value = "Token Controller")
@RestController
@RequestMapping("/token")
@ResponseBody
class TokenController(
    private val service: TokenService,
    private val userService: UserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private fun create(
        request: ServerRequest
    ): Mono<TokenView> {
        val tokenRequest = request.bodyToMono(TokenRequestInfo::class.java)

        return tokenRequest.flatMap { when (it.grantType) {
            GrantType.password -> createByPassword(request)
            else -> Mono.empty()
        } }
    }

    private fun createByPassword(request: ServerRequest)
        = request.bodyToMono(UserAuthorizeInfo::class.java)
        .flatMap(userService::authorize)
        .flatMap(service::issueAccessToken)
}