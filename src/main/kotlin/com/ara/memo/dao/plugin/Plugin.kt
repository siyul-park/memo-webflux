package com.ara.memo.dao.plugin

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface Plugin {
    fun <T> apply(mono: Mono<T>): Mono<T>
    fun <T> apply(flux: Flux<T>): Flux<T>
}