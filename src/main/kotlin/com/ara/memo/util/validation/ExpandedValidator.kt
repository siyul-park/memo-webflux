package com.ara.memo.util.validation

import com.ara.memo.util.error.Errors
import com.ara.memo.util.validation.exception.ValidationException
import com.ara.memo.util.validation.mapper.ConstraintViolationMapper
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import kotlin.reflect.KClass

@Component
class ExpandedValidator(
    private val validator: ReactiveValidator,
    private val constraintViolationMapper: ConstraintViolationMapper
) {
    fun <T : Any> validate(source: T, vararg groups: KClass<*>): Mono<T> {
        return validator.validate(source, *groups)
            .map(constraintViolationMapper::map)
            .collectList()
            .flatMap {
                when (it.isEmpty()) {
                    true -> Mono.just(source)
                    false -> Mono.error(ValidationException(Errors.of(it)))
                }
            }
    }
}