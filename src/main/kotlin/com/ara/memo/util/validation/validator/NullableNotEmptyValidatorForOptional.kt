package com.ara.memo.util.validation.validator

import com.ara.memo.util.validation.annotation.NullableNotEmpty
import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NullableNotEmptyValidatorForOptional : ConstraintValidator<NullableNotEmpty, Optional<*>> {
    override fun isValid(value: Optional<*>?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        return value.isPresent
    }
}