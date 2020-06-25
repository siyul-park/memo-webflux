package com.ara.memo.handler.token

import com.ara.memo.dto.authentication.AuthenticationPayload
import com.ara.memo.dto.authentication.GrantType
import com.ara.memo.dto.authentication.UserAuthenticationPayload
import com.ara.memo.util.server.payload.PayloadExtractor
import com.ara.memo.util.server.payload.PayloadMultiplexer
import com.ara.memo.util.validation.Checkpoint
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TokenHandler(
    checkpoint: Checkpoint
) {
    private val payloadExtractor = PayloadExtractor.of(AuthenticationPayload::class, checkpoint)
    private val payloadExtractorForPassword = PayloadExtractor.of(UserAuthenticationPayload::class, checkpoint)

    private val payloadMultiplexer: PayloadMultiplexer<GrantType> = PayloadMultiplexer { request ->
        request.body { inputMessage, context ->  }
        payloadExtractor.bodyToMono(request).map { it.grantType }
    }.also {
        it.register(GrantType.password, this::createByPassword)
    }

    fun create(request: ServerRequest) = payloadMultiplexer.handle(request)

    private fun createByPassword(request: ServerRequest): Mono<ServerResponse> {
        val payload = payloadExtractorForPassword.bodyToMono(request)
        return payload.flatMap {
            ServerResponse.noContent().build()
        }
    }
}