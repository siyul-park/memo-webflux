package com.ara.memo.util.validation.mapper

import com.ara.memo.util.error.SingleError
import com.ara.memo.util.error.factory.Errors
import com.ara.memo.util.mapper.Mapper
import javax.validation.ConstraintViolation

object ConstraintViolationMapper : Mapper<ConstraintViolation<*>, SingleError> {
    override fun map(source: ConstraintViolation<*>): SingleError {
        return Errors.from(source.message)
    }
}