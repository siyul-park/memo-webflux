package com.ara.memo.util.validation

import com.ara.memo.util.error.factory.Errors
import com.ara.memo.util.validation.exception.ValidationException
import com.ara.memo.util.validation.mapper.ConstraintViolationMapper
import reactor.core.publisher.Mono
import kotlin.reflect.KClass

class CheckpointAdapter(
    private val validator: ReactiveValidator,
    private val constraintViolationMapper: ConstraintViolationMapper
) : Checkpoint {
    override fun <T : Any> validate(source: T, vararg groups: KClass<*>): Mono<T> {
        return validator.validate(source, *groups)
            .map(constraintViolationMapper::map)
            .collectList()
            .flatMap {
                when (it.isEmpty()) {
                    true -> Mono.just(source)
                    false -> Mono.error(ValidationException(Errors.from(it)))
                }
            }
    }
}