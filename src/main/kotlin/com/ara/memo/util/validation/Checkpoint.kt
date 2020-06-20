package com.ara.memo.util.validation

import reactor.core.publisher.Mono
import kotlin.reflect.KClass

interface Checkpoint {
    fun <T : Any> validate(source: T, vararg groups: KClass<*>): Mono<T>
}