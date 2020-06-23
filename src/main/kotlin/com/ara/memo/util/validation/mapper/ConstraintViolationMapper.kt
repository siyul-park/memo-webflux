package com.ara.memo.util.validation.mapper

import com.ara.memo.util.error.SingleError
import com.ara.memo.util.error.factory.Errors
import com.ara.memo.util.mapper.Mapper
import org.springframework.stereotype.Component
import javax.validation.ConstraintViolation

@Component
class ConstraintViolationMapper : Mapper<ConstraintViolation<*>, SingleError> {
    override fun map(source: ConstraintViolation<*>) = Errors.from(source.message)
}