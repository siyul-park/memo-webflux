package com.ara.memo.handler

import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import javax.validation.ValidationException
import javax.validation.Validator
import kotlin.reflect.KClass

abstract class Handler(private val validator: Validator) {
    protected fun <T> valid(source: T, vararg groups: KClass<*>): Mono<T> = Mono.fromCallable {
        validator.validate(source, *(groups.map { it.java }.toTypedArray()))
    }.flatMap {
        when (it.isEmpty()) {
            true -> Mono.just(source)
            false -> {
                val messageBuilder = StringBuilder()
                it.forEach { violation -> messageBuilder.append(violation.message) }
                Mono.error(ValidationException(messageBuilder.toString()))
            }
        }
    }

    protected fun <T : Any, M : Any> ServerRequest.bodyWithJsonView(type: KClass<T>, mode: KClass<M>) = this.body(
        BodyExtractors.toMono(type.java),
        mapOf(
            Jackson2CodecSupport.JSON_VIEW_HINT to mode.java
        )
    )

    protected fun <V : Any, T : Any> ServerResponse.BodyBuilder.bodyWithJsonView(value: Mono<V>, view: KClass<V>, profile: KClass<T>) = this.hint(
        Jackson2CodecSupport.JSON_VIEW_HINT, profile.java
    ).body(BodyInserters.fromPublisher(value, view.java))
}