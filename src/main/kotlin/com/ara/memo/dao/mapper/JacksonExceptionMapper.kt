package com.ara.memo.dao.mapper

import com.ara.memo.exception.BadRequestException
import com.ara.memo.util.mapper.ExceptionMapper
import com.fasterxml.jackson.databind.JsonMappingException

object JacksonExceptionMapper : ExceptionMapper {
    override fun map(source: Throwable) = when (source) {
        is JsonMappingException -> BadRequestException(source.message)
        else -> source
    }
}