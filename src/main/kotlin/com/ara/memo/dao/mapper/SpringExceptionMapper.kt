package com.ara.memo.dao.mapper

import com.ara.memo.util.mapper.ExceptionMapper
import org.springframework.dao.DuplicateKeyException

object SpringExceptionMapper: ExceptionMapper {
    override fun map(source: Throwable) = when (source) {
        is DuplicateKeyException -> com.ara.memo.dao.exception.DuplicateKeyException(source.message)
        else -> source
    }
}