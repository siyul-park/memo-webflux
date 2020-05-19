package com.ara.memo.util.validation

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.ConstraintViolation
import javax.validation.metadata.BeanDescriptor
import kotlin.reflect.KClass

interface ReactiveValidator {
    fun <T : Any> validate(source: T, vararg groups: KClass<*>): Flux<ConstraintViolation<T>>

    fun <T> validateProperty(source: T, propertyName: String, vararg groups: KClass<*>): Flux<ConstraintViolation<T>>

    fun <T : Any> validateValue(beanType: KClass<T>, propertyName: String, value: Any, vararg groups: KClass<*>): Flux<ConstraintViolation<T>>

    fun getConstraintsForClass(clazz: KClass<*>): Mono<BeanDescriptor>

    fun <T : Any> unwrap(type: KClass<T>): Mono<T>
}