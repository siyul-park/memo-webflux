package com.ara.memo.util.server.payload

import com.ara.memo.util.validation.Checkpoint
import org.springframework.web.reactive.function.server.ServerRequest
import kotlin.reflect.KClass

class PayloadExtractor<P : Any>(
    private val payload: KClass<P>,
    private val checkpoint: Checkpoint
) {
    fun bodyToMono(request: ServerRequest) = request.bodyToMono(payload.java)
        .flatMap { checkpoint.validate(it) }

    fun bodyToFlux(request: ServerRequest) = request.bodyToFlux(payload.java)
        .flatMap { checkpoint.validate(it) }

    companion object {
        fun <P: Any> of(payload: KClass<P>, checkpoint: Checkpoint) = PayloadExtractor(payload, checkpoint)
    }
}