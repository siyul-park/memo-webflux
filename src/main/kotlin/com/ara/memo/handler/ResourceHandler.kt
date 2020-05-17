package com.ara.memo.handler

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import javax.validation.Validator
import kotlin.reflect.KClass

abstract class ResourceHandler<R : Any, V : Any>(
    validator: Validator,
    private val requestType: KClass<R>,
    private val viewType: KClass<V>
) : Handler(validator) {
    protected fun <T : Any> ServerRequest.bodyWithJsonView(mode: KClass<T>) = this.bodyWithJsonView(requestType, mode)

    protected fun <T : Any> ServerResponse.BodyBuilder.bodyWithJsonView(value: Mono<V>, profile: KClass<T>) = this.bodyWithJsonView(value, viewType, profile)
}