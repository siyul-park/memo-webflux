package com.ara.memo.dao.mapper

import org.springframework.dao.DuplicateKeyException

object SpringExceptionMapper: ExceptionMapper {
    override fun map(error: Throwable) = when (error) {
        is DuplicateKeyException -> com.ara.memo.dao.exception.DuplicateKeyException(error.message)
        else -> error
    }
}