package com.ara.memo.util.validation

import reactor.core.publisher.Mono
import javax.validation.Validator
import kotlin.reflect.KClass

class ReactiveValidatorAdapter(
    private val validator: Validator
) : ReactiveValidator {
    override fun <T : Any> validate(source: T, vararg groups: KClass<*>) = Mono.fromCallable {
        validator.validate(source, *convertToJavaClass(groups))
    }.flatMapIterable { it }

    override fun <T> validateProperty(source: T, propertyName: String, vararg groups: KClass<*>) = Mono.fromCallable {
        validator.validateProperty(source, propertyName, *convertToJavaClass(groups))
    }.flatMapIterable { it }

    override fun <T : Any> validateValue(beanType: KClass<T>, propertyName: String, value: Any, vararg groups: KClass<*>) = Mono.fromCallable {
        validator.validateValue(beanType.java, propertyName, value, *convertToJavaClass(groups))
    }.flatMapIterable { it }

    override fun getConstraintsForClass(clazz: KClass<*>) = Mono.fromCallable {
        validator.getConstraintsForClass(clazz.java)
    }

    override fun <T : Any> unwrap(type: KClass<T>) = Mono.fromCallable {
        validator.unwrap(type.java)
    }

    private fun convertToJavaClass(groups: Array<out KClass<*>>): Array<Class<out Any>> {
        return groups.map { it.java }.toTypedArray()
    }
}