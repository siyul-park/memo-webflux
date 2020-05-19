package com.ara.memo.dao.user.mapper

import com.ara.memo.dao.exception.DuplicateKeyException
import com.ara.memo.dao.user.exception.UserAlreadyExistException
import com.ara.memo.util.mapper.ExceptionMapper

object UserExceptionMapper: ExceptionMapper {
    override fun map(source: Throwable) = when (source) {
        is DuplicateKeyException -> UserAlreadyExistException(source.message)
        else -> source
    }
}