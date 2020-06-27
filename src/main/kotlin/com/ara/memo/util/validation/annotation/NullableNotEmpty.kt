package com.ara.memo.util.validation.annotation

import com.ara.memo.util.validation.validator.NullableNotEmptyValidatorForOptional
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [NullableNotEmptyValidatorForOptional::class])
annotation class NullableNotEmpty(
    val message: String = "{javax.validation.constraints.NotNull.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)