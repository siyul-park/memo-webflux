package com.ara.memo.dao.user.mapper

import com.ara.memo.dao.exception.DuplicateKeyException
import com.ara.memo.dao.mapper.ExceptionMapper
import com.ara.memo.dao.user.exception.UserAlreadyExistException

object UserExceptionMapper: ExceptionMapper {
    override fun map(error: Throwable) = when (error) {
        is DuplicateKeyException -> UserAlreadyExistException(error.message)
        else -> error
    }
}