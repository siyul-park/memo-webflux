package com.ara.memo.util.validation.mapper

import com.ara.memo.util.error.factory.Errors
import org.springframework.stereotype.Component
import javax.validation.ConstraintViolation

@Component
class ConstraintViolationMapperImp : ConstraintViolationMapper {
    override fun map(source: ConstraintViolation<*>) = Errors.from(source.message)
}