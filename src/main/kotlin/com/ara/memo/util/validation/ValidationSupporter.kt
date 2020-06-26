package com.ara.memo.util.validation

import com.ara.memo.util.refrect.KotlinSupporter
import reactor.core.publisher.Mono
import javax.validation.ConstraintViolation
import javax.validation.Validator
import kotlin.reflect.KClass

abstract class ValidationSupporter(
    private val validator: Validator
) {
    fun <T : Any> validate(source: T, vararg groups: KClass<*>): Mono<T> {
        return Mono.fromCallable { validator.validate(source, *KotlinSupporter.convertToClass(groups)) }
            .flatMap {
                when (it.isEmpty()) {
                    true -> onSuccess(source)
                    false -> onError(it, source)
                }
            }
    }

    protected abstract fun <T> onSuccess(source: T): Mono<T>
    protected abstract fun <T> onError(constraintViolation: Set<ConstraintViolation<T>>, source: T): Mono<T>
}