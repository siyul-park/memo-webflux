package com.ara.memo.util.validation

import com.ara.memo.util.error.factory.Errors
import com.ara.memo.util.plugin.Plugin
import com.ara.memo.util.refrect.KotlinExtentions
import com.ara.memo.util.validation.exception.ConstraintViolationError
import com.ara.memo.util.validation.mapper.ConstraintViolationMapper
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Validator
import kotlin.reflect.KClass

class ValidationPlugin(
    private val validator: Validator,
    private val clazz: Array<out KClass<*>>
) : Plugin {
    override fun <T> apply(mono: Mono<T>): Mono<T> {
        return mono.doOnNext(this::validate)
    }

    override fun <T> apply(flux: Flux<T>): Flux<T> {
        return flux.doOnNext(this::validate)
    }

    private fun <T> validate(entity: T) {
        val violation = validator.validate(entity, *KotlinExtentions.convertToClass(clazz))
        val errors = violation.map(ConstraintViolationMapper::map)
        if (errors.isNotEmpty()) throw ConstraintViolationError(Errors.from(errors))
    }

    companion object {
        fun of(validator: Validator, vararg clazz: KClass<*>) = ValidationPlugin(validator, clazz)
    }
}