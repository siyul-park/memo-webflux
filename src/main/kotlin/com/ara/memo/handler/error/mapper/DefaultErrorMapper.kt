package com.ara.memo.handler.error.mapper

import com.ara.memo.entity.error.Error
import com.ara.memo.util.mapper.ErrorMapper
import org.springframework.stereotype.Component

@Component
class DefaultErrorMapper : ErrorMapper {
    override fun map(source: Throwable) = Error(source.message ?: "Unknown error.", source.cause)
}
