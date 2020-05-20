package com.ara.memo.handler.error.mapper

import com.ara.memo.util.error.Error
import com.ara.memo.util.error.ErrorProvider
import com.ara.memo.util.error.Errors
import com.ara.memo.util.mapper.ErrorMapper
import org.springframework.stereotype.Component

@Component
class DefaultErrorMapper : ErrorMapper {
    override fun map(source: Throwable): Error = when (source) {
        is ErrorProvider -> source.get()
        else -> Errors.of(source.message)
    }
}
