package com.ara.memo.util.validation

import com.ara.memo.util.error.factory.Errors
import com.ara.memo.util.validation.exception.ConstraintViolationError
import com.ara.memo.util.validation.mapper.ConstraintViolationMapper
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.ConstraintViolation
import javax.validation.Validator

@Component
class DefaultValidationSupporter(
    validator: Validator
) : ValidationSupporter(validator) {
    override fun <T> onSuccess(source: T): Mono<T> {
        return Mono.fromCallable { source }
    }

    override fun <T> onError(constraintViolation: Set<ConstraintViolation<T>>, source: T): Mono<T> {
        return Flux.fromIterable(constraintViolation)
            .map(ConstraintViolationMapper::map)
            .collectList()
            .map(Errors::from)
            .flatMap { Mono.error<T>(ConstraintViolationError(it)) }
    }
}