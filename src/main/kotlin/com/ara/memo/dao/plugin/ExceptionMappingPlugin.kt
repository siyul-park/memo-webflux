package com.ara.memo.dao.plugin

import com.ara.memo.dao.mapper.ExceptionMapper
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

open class ExceptionMappingPlugin(
    private val exceptionMapper: ExceptionMapper
) : Plugin {
    override fun <T> apply(mono: Mono<T>): Mono<T> {
        return mono.onErrorMap(exceptionMapper::map)
    }

    override fun <T> apply(flux: Flux<T>): Flux<T> {
        return flux.onErrorMap(exceptionMapper::map)
    }
}